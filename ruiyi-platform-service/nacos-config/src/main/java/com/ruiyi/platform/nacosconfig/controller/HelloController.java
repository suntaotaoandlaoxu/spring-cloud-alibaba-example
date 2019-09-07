package com.ruiyi.platform.nacosconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//支持配置中心属性热加载
@RefreshScope
@RestController
@RequestMapping("/test")
public class HelloController {
    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private String userAge;
    @GetMapping("/user")
    public String index() {
        return "hello userName：" + userName + "，userAge：" + userAge + "，this is nacos-config";
    }
}