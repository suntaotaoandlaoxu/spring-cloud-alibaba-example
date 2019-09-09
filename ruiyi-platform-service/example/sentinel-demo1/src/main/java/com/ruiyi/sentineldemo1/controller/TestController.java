package com.ruiyi.sentineldemo1.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ruiyi.sentineldemo1.feign.SentinelDemo2FeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

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
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private SentinelDemo2FeignClient sentinelDemo2FeignClient;

    @GetMapping("/test")
    @SentinelResource(value = "test-sentinel", blockHandler = "testBlock", fallback = "testFallback")
    public String test(@RequestParam(required = false) String a){
        return "test-sentinel1" + " " + a;
    }

    public String testBlock(String a, BlockException e){
        System.out.println("报错了：" + e.getMessage());
        return "test-sentinel1" + " " + "限流或降级了";
    }

    public String testFallback(@RequestParam(required = false) String a){
        return "test-sentinel1" + " " + "fallback了";
    }

    @GetMapping("/testRestTemplate/{a}")
    public String testRestTemplate(@PathVariable String a){
        return this.restTemplate.getForObject("http://sentinel-demo2/test?a=2", String.class);
    }

    @GetMapping("/testFeign")
    public String testFeign(@RequestParam(required = false) String a){
        return sentinelDemo2FeignClient.test(a);
    }
}
