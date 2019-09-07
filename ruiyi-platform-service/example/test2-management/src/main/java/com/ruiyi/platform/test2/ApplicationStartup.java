package com.ruiyi.platform.test2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;

//初始化
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

	static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

	@Value("${server.address}")
	private String serverAddress;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		/*
		@FeignClient引用了其他模块，每多引用一个模块监听都会执行一遍，保证本项目只执行一边
		application的parent属于bootstrap
		 */
	}

}
