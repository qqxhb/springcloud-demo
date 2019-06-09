package com.qqxhb.springcloud.api.course;

import com.qqxhb.springcloud.domain.course.response.CoursePublishResult;
import com.qqxhb.springcloud.model.response.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 */
@Api("课程管理接口")
public interface CourseControllerApi {

	@ApiOperation("添加课程图片")
	public ResponseResult addCoursePic(String courseId, String pic);

	@ApiOperation("删除课程图片")
	public ResponseResult deleteCoursePic(String courseId);

	@ApiOperation("课程预览")
	public CoursePublishResult preview(String id);
}
