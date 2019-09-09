package com.ruiyi.sentineldemo2.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Author: suntaotao
 * Date: 2019/9/7
 * Time: 17:22
 * Describe:
 * To change this template use File | Settings | File Templates.
 */

@RestController
public class TestController {

    @GetMapping("/test")
    @SentinelResource(value = "test-sentinel", blockHandler = "testBlock", fallback = "testFallback")
    public String test(@RequestParam(required = false) String a){
        return "test-sentinel2" + " " + a;
    }

    public String testBlock(String a, BlockException e){
        System.out.println("报错了：" + e.getMessage());
        return "test-sentinel2" + " " + "限流或降级了";
    }

    public String testFallback(@RequestParam(required = false) String a){
        return "test-sentinel2" + " " + "fallback了";
    }
}
