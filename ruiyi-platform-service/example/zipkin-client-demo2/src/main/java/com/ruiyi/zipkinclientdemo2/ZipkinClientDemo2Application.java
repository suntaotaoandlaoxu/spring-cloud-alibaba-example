package com.ruiyi.zipkinclientdemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ZipkinClientDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinClientDemo2Application.class, args);
	}

}
