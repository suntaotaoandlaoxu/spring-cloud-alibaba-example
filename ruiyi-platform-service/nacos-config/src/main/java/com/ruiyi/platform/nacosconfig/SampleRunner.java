package com.ruiyi.platform.nacosconfig;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.Executor;

//初始化
@Component
public class SampleRunner implements org.springframework.boot.ApplicationRunner {

	@Autowired
	private NacosConfigProperties nacosConfigProperties;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		nacosConfigProperties.configServiceInstance().addListener(
				"nacos-config.properties", "TEST_GROUP", new Listener() {

					/**
					 * Callback with latest config data.
					 *
					 * For example, config data in Nacos is:
					 *
					 * user.name=Nacos user.age=25
					 *
					 * @param configInfo latest config data for specific dataId in Nacos
					 * server
					 */
					@Override
					public void receiveConfigInfo(String configInfo) {
						Properties properties = new Properties();
						try {
							properties.load(new StringReader(configInfo));
						}
						catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("config changed: " + properties);
					}

					@Override
					public Executor getExecutor() {
						return null;
					}
				});
	}

}
