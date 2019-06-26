package com.qqxhb.service.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.qqxhb.springcloud.interceptor.FeignClientInterceptor;

/**
 * @author Administrator
 * @version 1.0
 **/
@EnableFeignClients // 开始feignClient
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.qqxhb.springcloud.domain.course") // 扫描实体类
@ComponentScan(basePackages = { "com.qqxhb.springcloud.api" }) // 扫描接口
@ComponentScan(basePackages = { "com.qqxhb.springcloud" }) // 扫描common包下的类
@ComponentScan(basePackages = { "com.qqxhb.service.course" })
public class CourseApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(CourseApplication.class, args);
	}

	@Bean
	@LoadBalanced // 开始客户端负载均衡
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}

	@Bean
	public FeignClientInterceptor getFeignClientInterceptor() {
		return new FeignClientInterceptor();
	}
}
