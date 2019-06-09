package com.qqxhb.springcloud.api.teacher;

import com.qqxhb.springcloud.domain.teacher.request.QueryRequest;
import com.qqxhb.springcloud.domain.teacher.response.TeacherResult;
import com.qqxhb.springcloud.model.response.QueryResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("教师管理接口")
public interface TeacherControllerApi {
	@ApiOperation("查询教师列表接口")
	public QueryResponseResult findList(int page, int size, QueryRequest queryRequest);

	@ApiOperation("根据ID查询教师接口")
	public TeacherResult getTeacherById(long teacherId);

}
