package com.qqxhb.service.teacher.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qqxhb.springcloud.domain.teacher.Teacher;
import com.qqxhb.springcloud.domain.teacher.request.QueryRequest;
import com.qqxhb.springcloud.domain.teacher.response.TeacherResult;
import com.qqxhb.springcloud.model.response.CommonCode;
import com.qqxhb.springcloud.model.response.QueryResponseResult;
import com.qqxhb.springcloud.model.response.QueryResult;
import com.qqxhb.springcloud.model.response.ResponseResult;

@Service
public class TeacherService {

	/**
	 * 列表查询方法
	 * 
	 * @param page         页码，从1开始记数
	 * @param size         每页记录数
	 * @param queryRequest 查询条件
	 * @return
	 */
	public QueryResponseResult findList(int page, int size, QueryRequest queryRequest) {
		QueryResult queryResult = new QueryResult();
		QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
		return queryResponseResult;
	}

	// 根据id删除页面
	public ResponseResult delete(String id) {
		return new ResponseResult(CommonCode.FAIL);
	}

	// 页面发布
	public ResponseResult publish(String pageId) {
		return new ResponseResult(CommonCode.SUCCESS);
	}

	// 查询教师
	public TeacherResult getTeacherById(long teacherId) {
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		teacher.setName("测试");
		return new TeacherResult(CommonCode.SUCCESS, teacher);
	}

}
