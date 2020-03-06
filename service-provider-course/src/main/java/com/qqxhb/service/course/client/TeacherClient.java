package com.qqxhb.service.course.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.qqxhb.springcloud.domain.teacher.response.TeacherResult;

@FeignClient(value = "SERVICE-PROVIDER-TEACHER",fallback = TeacherServiceFallBack.class) // 指定远程调用的服务名，配置Hystrix降级回调类
public interface TeacherClient {
	@GetMapping("/teacher/test/{id}") // 用GetMapping标识远程调用的http的方法类型
	public String getTeacherTest(@PathVariable("id") long teacherId);
	
	@GetMapping("/teacher/{id}") // 用GetMapping标识远程调用的http的方法类型
	public TeacherResult getTeacherById(@PathVariable("id") long teacherId);
}
