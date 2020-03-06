package com.qqxhb.service.course.client;

import com.qqxhb.springcloud.domain.teacher.Teacher;
import com.qqxhb.springcloud.domain.teacher.response.TeacherResult;
import com.qqxhb.springcloud.model.response.CommonCode;
/**
 * 
 * 教师微服务调用失败，降级策略
 *
 */
public class TeacherServiceFallBack implements TeacherClient {

	@Override
	public String getTeacherTest(long teacherId) {
		return "教师服务暂不可用，回调测试";
	}

	@Override
	public TeacherResult getTeacherById(long teacherId) {
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		teacher.setName("默认教师");
		return new TeacherResult(CommonCode.SUCCESS, teacher);
	}

}
