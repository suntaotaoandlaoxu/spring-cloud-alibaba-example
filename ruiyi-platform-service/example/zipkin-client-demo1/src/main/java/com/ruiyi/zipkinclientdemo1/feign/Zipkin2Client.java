package com.ruiyi.zipkinclientdemo1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * Author: suntaotao
 * Date: 2019/9/5
 * Time: 17:36
 * Describe:
 * To change this template use File | Settings | File Templates.
 */

@FeignClient(name = "zipkin-client-demo2")
public interface Zipkin2Client {

    @GetMapping("/zipkin2/test/hello")
    String hello();
}
