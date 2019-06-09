package com.qqxhb.service.course.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "SERVICE-PROVIDER-TEACHER") // 指定远程调用的服务名
public interface TeacherClient {
	@GetMapping("/teacher/test/{id}") // 用GetMapping标识远程调用的http的方法类型
	public String getTeacherById(@PathVariable("id") long teacherId);
}
