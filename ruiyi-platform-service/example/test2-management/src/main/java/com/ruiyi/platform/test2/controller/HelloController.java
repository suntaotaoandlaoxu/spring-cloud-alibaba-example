package com.ruiyi.platform.test2.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//支持配置中心属性热加载
@RefreshScope
@RestController
@RequestMapping("/api/v1")
public class HelloController {



	
    @GetMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is test2";
    }

    @GetMapping("/hello2")
    public String hello2(@RequestParam String name) {
        return "hello2 "+name+"，this is test2";
    }


   // @GetMapping("/configDetail")
    public String configDetail() {
        return "123";
    }
}