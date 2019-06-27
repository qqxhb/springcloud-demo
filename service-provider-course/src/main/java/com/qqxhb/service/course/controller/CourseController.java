package com.qqxhb.service.course.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qqxhb.service.course.client.TeacherClient;
import com.qqxhb.service.course.service.CourseService;
import com.qqxhb.springcloud.api.course.CourseControllerApi;
import com.qqxhb.springcloud.domain.course.response.CoursePublishResult;
import com.qqxhb.springcloud.domain.teacher.response.TeacherResult;
import com.qqxhb.springcloud.model.response.ResponseResult;
import com.qqxhb.springcloud.utils.Oauth2Util;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping
public class CourseController implements CourseControllerApi {

	@Autowired
	CourseService courseService;

	@Override
	@PreAuthorize("add_course_pic")
	@PostMapping("/coursepic/add")
	public ResponseResult addCoursePic(@RequestParam("courseId") String courseId, @RequestParam("pic") String pic) {
		return courseService.addCoursePic(courseId, pic);
	}

	@Override
	@PreAuthorize("delete_course_pic")
	@DeleteMapping("/coursepic/{id}")
	public ResponseResult deleteCoursePic(@PathVariable("id") String courseId) {
		return courseService.deleteCoursePic(courseId);
	}

	@Override
	@PreAuthorize("preview_course")
	@PostMapping("/preview/{id}")
	public CoursePublishResult preview(@PathVariable("id") String id) {
		// 获取当前用户信息
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Oauth2Util oauth2Util = new Oauth2Util();
		Oauth2Util.UserJwt userJwt = oauth2Util.getUserJwtFromHeader(request);
		return courseService.preview(id);

	}

	@Autowired
	TeacherClient teacherClient;

	@GetMapping("/testTeacher")
	public String testTeacher() {
		return teacherClient.getTeacherTest(1L);

	}

	@GetMapping("/getTeacher")
	public TeacherResult getTeacher() {
		return teacherClient.getTeacherById(1L);

	}

}
