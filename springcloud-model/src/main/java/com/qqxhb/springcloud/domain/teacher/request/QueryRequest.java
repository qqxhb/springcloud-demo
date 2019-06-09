package com.qqxhb.springcloud.domain.teacher.request;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class QueryRequest {
	// 接收查询条件
	// 教师ID
	private String teacherId;
	// 教师名称
	private String teacherName;
}
