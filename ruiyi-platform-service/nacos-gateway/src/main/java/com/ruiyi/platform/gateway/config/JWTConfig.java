package com.ruiyi.platform.gateway.config;

import com.alibaba.nacos.client.utils.StringUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class JWTConfig implements GlobalFilter, Ordered {

    private static final String SECRET = "1_@_RUIYI_@_1";

    public static final String TOKEN_PREFIX = "Bearer";

    @Value("${skipAuthUrls:/service-common/login,/service-common/api/v1/token/validToken,/kb-management/api/v1/kbInfoExtend/checkData}")
    private String[] skipAuthUrls;

    /**
     * JWT验证
     * @param token
     * @return userName
     */
    private Result verifyJWT(String token){
        Result result = new Result();
        result.setCode("");
        result.setMessage("");
        String userName = "";
        Map<String,String> resultmap = new HashMap<String,String>();
        if (token != null) {
            // parse the token.
            Map<String,Object> body = new HashMap<>();
            try{
                body = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
            }catch (Exception e){
                result.setCode("500");
                result.setMessage("令牌错误");
                return result;
            }
            userName = (String) (body.get("userName"));
            String userNo = (String) (body.get("userNo"));
            String appid = (String) (body.get("APPID"));
            String hospitalid = (String) (body.get("hospitalid"));

            long expiryTime = (long) body.get("expiryTime");
            long tokenExpTime = (long) body.get("tokenExpTime");
            String randomValue = (String) body.get("randomValue");

            resultmap.put("userNo",userNo);
            resultmap.put("userName",userName);
            resultmap.put("appid",appid);
            resultmap.put("hospitalid",hospitalid);
            resultmap.put("expiryTime",String.valueOf(expiryTime));
            resultmap.put("tokenExpTime",String.valueOf(tokenExpTime));
            resultmap.put("randomValue",randomValue);
            if(expiryTime < System.currentTimeMillis()){
                result.setCode("500");
                result.setMessage("token已经过期");
                return result;
            }
            if(userName == null || userName.isEmpty()){
                return null;
            } else{
                result.setCode("");
                result.setMessage(userName);
                return result;
            }
        }else{
            result.setCode("500");
            result.setMessage("Missing token");
            return result;
        }
    }

    /**
     * 返回的code值代表的含义
     * 401  表示token穿入为空
     * -100100   token
     *
     * @param exchange
     * @param chain
     * @return
     */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        //跳过不需要验证的路径
        if(Arrays.asList(skipAuthUrls).contains(url)){
            return chain.filter(exchange);
        }
        //从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        //未携带token或token在黑名单内
        if (token == null || token.isEmpty()) {
//            if (token == null || token.isEmpty() || isBlackToken(token)) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"401\",\"message\": \"401 Unauthorized.\"}"
                    .getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        //取出token包含的身份
        Result result = verifyJWT(token);
        String code = result.getCode();
        String message = result.getMessage();
        String userName = "";
        if(StringUtils.isNotEmpty(code)){
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = ("{\"code\": \"" + code + "\",\"message\": \"" + message + "\"}")
                    .getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        } else {
            userName = message;

        }
        if(userName.isEmpty()){
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"10002\",\"message\": \"invalid token.\"}"
                    .getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        //将现在的request，添加当前身份
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header("Authorization-UserName", userName).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }

    public class Result{
        private String code;

        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
