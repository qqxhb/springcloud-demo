package com.qqxhb.service.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qqxhb.service.course.service.CourseService;
import com.qqxhb.springcloud.api.course.CourseControllerApi;
import com.qqxhb.springcloud.domain.course.response.CoursePublishResult;
import com.qqxhb.springcloud.model.response.ResponseResult;

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
	@PostMapping("/coursepic/add")
	public ResponseResult addCoursePic(@RequestParam("courseId") String courseId, @RequestParam("pic") String pic) {
		return courseService.addCoursePic(courseId, pic);
	}

	@Override
	@DeleteMapping("/coursepic/{id}")
	public ResponseResult deleteCoursePic(@PathVariable("id") String courseId) {
		return courseService.deleteCoursePic(courseId);
	}

	@Override
	@PostMapping("/preview/{id}")
	public CoursePublishResult preview(@PathVariable("id") String id) {
		return courseService.preview(id);

	}
}
