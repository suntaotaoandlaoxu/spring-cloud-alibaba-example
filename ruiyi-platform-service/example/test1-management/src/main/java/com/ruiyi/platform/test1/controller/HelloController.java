package com.ruiyi.platform.test1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Author: suntaotao
 * Date: 2019/8/31
 * Time: 10:37
 * Describe:
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is test1";
    }

}
