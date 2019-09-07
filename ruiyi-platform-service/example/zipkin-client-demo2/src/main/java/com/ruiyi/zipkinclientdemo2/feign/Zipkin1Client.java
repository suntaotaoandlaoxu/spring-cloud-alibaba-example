package com.ruiyi.zipkinclientdemo2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Author: suntaotao
 * Date: 2019/9/5
 * Time: 17:36
 * Describe:
 * To change this template use File | Settings | File Templates.
 */

@FeignClient(name = "zipkin-client-demo1")
public interface Zipkin1Client {

    @GetMapping("/zipkin1/test/hello")
    String hello();
}
