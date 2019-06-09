package com.qqxhb.service.teacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient // 一个EurekaClient从EurekaServer发现服务
@SpringBootApplication
@EntityScan("com.qqxhb.springcloud.domain.teacher") // 扫描实体类
@ComponentScan(basePackages = { "com.qqxhb.springcloud.api" }) // 扫描接口
@ComponentScan(basePackages = { "com.qqxhb.springcloud" }) // 扫描common包下的类
@ComponentScan(basePackages = { "com.qqxhb.service.teacher" }) // 扫描本项目下的所有类
public class TeacherApplication {
	public static void main(String[] args) {
		SpringApplication.run(TeacherApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}

}
