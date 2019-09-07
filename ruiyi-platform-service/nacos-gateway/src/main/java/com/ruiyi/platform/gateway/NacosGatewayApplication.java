package com.ruiyi.platform.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient  // 启用服务注册和发现
@RestController  // 提供一个简单的降级页面
public class NacosGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosGatewayApplication.class, args);
    }



    
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        // Mono是一个Reactive stream，对外输出一个“fallback”字符串。
        return Mono.just("fallback");
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                // 配制一个路由,把 http://网关地址:网关端口/demo/ 下的请求路由到 demo-service 微服务中
//                .route(p -> p
//                        .path("/api/v*/**")
////                        .filters(f -> f
////                                .hystrix(config -> config   // 对path()指定的请求使用熔断器
////                                        .setName("mycmd")   // 熔断器的名字
////                                        .setFallbackUri("forward:/fallback"))
//////                                .stripPrefix(1)
////                        )  // 熔断到 /fallback, 就是上面配制的那个
//                        .uri("lb://demo-service"))  // 将请求路由到指定目标, lb开头是注册中心中的服务, http/https 开头
//                .build();
//
////        return builder.routes()
////                // 配制一个路由,把 http://网关地址:网关端口/demo/ 下的请求路由到 demo-service 微服务中
////                .route("service-test2-management", p -> p
////                        .path("/api/v*/**") //url匹配
////                        .filters(f -> f
////                                .hystrix(config -> config   // 对path()指定的请求使用熔断器
////                                        .setName("mycmd")   // 熔断器的名字
////                                        .setFallbackUri("forward:/fallback")))  // 熔断到 /fallback, 就是上面配制的那个
////                        .uri("lb://service-test2-management"))  // 将请求路由到指定目标, lb开头是注册中心中的服务, http/https 开头你懂的
////                .build();
//
////        return builder.routes()
////                // 配制一个路由,把 http://网关地址:网关端口/demo/ 下的请求路由到 demo-service 微服务中
////                .route(p -> p
////                        .path("/api/v*/**")
////                        .filters(f -> f
////                                .hystrix(config -> config   // 对path()指定的请求使用熔断器
////                                        .setName("mycmd")   // 熔断器的名字
////                                        .setFallbackUri("forward:/fallback")))  // 熔断到 /fallback, 就是上面配制的那个
////                        .uri("lb://demo-service/"))  // 将请求路由到指定目标, lb开头是注册中心中的服务, http/https 开头
////                .build();
//    }


    /*
    Spring Cloud Gateway 与 zuul 的代理的地址有点不一样
    zuul 中, 例如我们配制的是 把 /demo/** 路由到  http://服务/, 则网关的请求地址: http://网关/demo/xx/abc.do 实际请求的服务地址为: http://服务/xx/abc.do,
     zuul自动把 /demo 去掉了.而 Spring Cloud Gateway 不是这样:在Spring Cloud Gateway中,上面的实际请求的服务地址为: http://服务/demo/xx/abc.do ,Spring Cloud Gateway不会把 /demo 去掉,与请求的地址一样
     */

}
