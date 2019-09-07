package com.ruiyi.zipkinclientdemo1.controller;

import com.ruiyi.zipkinclientdemo1.feign.Zipkin2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * Author: suntaotao
 * Date: 2019/9/5
 * Time: 17:33
 * Describe:
 * To change this template use File | Settings | File Templates.
 */

@RestController
@RequestMapping("/zipkin1/test")
public class HelloController {

    @Resource
    private Zipkin2Client zipkin2Client;

    @GetMapping("/hello")
    public String hello(){
        return "zipkin1";
    }

    @GetMapping("/feignHello")
    public String feignHello(){
        return zipkin2Client.hello();
    }
}
