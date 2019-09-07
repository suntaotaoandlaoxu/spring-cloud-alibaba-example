package com.ruiyi.platform.test1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import static com.ruiyi.platform.utils.configurer.Swagger2Configurer.CONTROLLER_PACKAGE;

@EnableDiscoveryClient
@SpringBootApplication
@ImportResource({"classpath:/applicationContext.xml"})
@EnableFeignClients
public class SpringCloudTest1Application {

	public static void main(String[] args) {

		CONTROLLER_PACKAGE = "com.ruiyi.platform.test1.config";

		SpringApplication.run(SpringCloudTest1Application.class, args);
	}

}
