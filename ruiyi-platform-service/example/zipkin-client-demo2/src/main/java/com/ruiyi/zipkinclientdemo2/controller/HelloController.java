package com.ruiyi.zipkinclientdemo2.controller;

import com.ruiyi.zipkinclientdemo2.feign.Zipkin1Client;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/zipkin2/test")
public class HelloController {

    @Resource
    private Zipkin1Client zipkin1Client;

    @GetMapping("/hello")
    public String hello() throws Exception{
        throw new Exception();
//        return "zipkin2";
    }

    @GetMapping("/feignHello")
    public String feignHello(){
        return zipkin1Client.hello();
    }
}
