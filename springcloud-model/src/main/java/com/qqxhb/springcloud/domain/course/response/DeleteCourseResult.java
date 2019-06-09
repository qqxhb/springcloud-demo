package com.qqxhb.springcloud.domain.course.response;

import com.qqxhb.springcloud.model.response.ResponseResult;
import com.qqxhb.springcloud.model.response.ResultCode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class DeleteCourseResult extends ResponseResult {
	public DeleteCourseResult(ResultCode resultCode, String courseId) {
		super(resultCode);
		this.courseId = courseId;
	}

	private String courseId;

}
