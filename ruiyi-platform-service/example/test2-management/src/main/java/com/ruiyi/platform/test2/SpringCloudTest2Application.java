package com.ruiyi.platform.test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import static com.ruiyi.platform.core.configurer.MybatisConfigurer.MAPPER_PACKAGE;
import static com.ruiyi.platform.core.configurer.MybatisConfigurer.MODEL_PACKAGE;
import static com.ruiyi.platform.utils.configurer.Swagger2Configurer.CONTROLLER_PACKAGE;


/**
 * 开启服务发现
 */
@EnableDiscoveryClient
@SpringBootApplication
@ImportResource({"classpath:/applicationContext.xml"})
@EnableFeignClients
public class SpringCloudTest2Application {
    public static void main(String[] args) {

        /**
         * 扫描功能在core包 和 utils包中，此处指用负责增加扫描区域
         */
        CONTROLLER_PACKAGE = "com.ruiyi.platform.test2.config";

        SpringApplication.run(SpringCloudTest2Application.class, args);
    }
}
