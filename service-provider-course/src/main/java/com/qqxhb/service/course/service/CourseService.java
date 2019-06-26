package com.qqxhb.service.course.service;

import org.springframework.stereotype.Service;

import com.qqxhb.springcloud.domain.course.response.CoursePublishResult;
import com.qqxhb.springcloud.model.response.CommonCode;
import com.qqxhb.springcloud.model.response.ResponseResult;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class CourseService {
	// 向课程管理数据添加课程与图片的关联信息
	public ResponseResult addCoursePic(String courseId, String pic) {
		return new ResponseResult(CommonCode.SUCCESS);
	}

	// 删除课程图片
	public ResponseResult deleteCoursePic(String courseId) {
		return new ResponseResult(CommonCode.FAIL);
	}

	// 课程预览
	public CoursePublishResult preview(String id) {
		// 拼装页面预览的url
		String url = "previewUrl" + id;
		// 返回CoursePublishResult对象（当中包含了页面预览的url）
		return new CoursePublishResult(CommonCode.SUCCESS, url);
	}
}
