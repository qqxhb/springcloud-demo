package com.qqxhb.service.teacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qqxhb.service.teacher.service.TeacherService;
import com.qqxhb.springcloud.api.teacher.TeacherControllerApi;
import com.qqxhb.springcloud.domain.teacher.request.QueryRequest;
import com.qqxhb.springcloud.domain.teacher.response.TeacherResult;
import com.qqxhb.springcloud.model.response.QueryResponseResult;

@RestController
@RequestMapping
public class TeacherController implements TeacherControllerApi {

	@Autowired
	TeacherService teacherService;

	@Override
	@GetMapping
	public QueryResponseResult findList(int page, int size, QueryRequest queryRequest) {
		return teacherService.findList(page, size, queryRequest);
	}

	@Value("${server.port}")
	int port;

	@Override
	@GetMapping("{id}")
	public TeacherResult getTeacherById(@PathVariable("id") long teacherId) {
		System.out.println("==================访问教师微服务，端口号：" + port);
		return teacherService.getTeacherById(teacherId);
	}

	@GetMapping("test/{id}")
	public String getTeacherTest(@PathVariable("id") long teacherId) {
		System.out.println("=============访问教师微服务，端口号：" + port + " teacherId:" + teacherId);
		return "success";
	}

}
