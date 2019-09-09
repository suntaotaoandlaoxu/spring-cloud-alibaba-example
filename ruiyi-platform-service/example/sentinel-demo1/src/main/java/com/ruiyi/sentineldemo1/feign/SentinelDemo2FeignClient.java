package com.ruiyi.sentineldemo1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * Author: suntaotao
 * Date: 2019/9/7
 * Time: 17:54
 * Describe:
 * To change this template use File | Settings | File Templates.
 */

@FeignClient(name = "sentinel-demo2")
public interface SentinelDemo2FeignClient {

    @GetMapping("/test")
    String test(@RequestParam(value = "a", required = false) String a);
}
