package com.qqxhb.springcloud.domain.teacher.response;

import com.qqxhb.springcloud.domain.teacher.Teacher;
import com.qqxhb.springcloud.model.response.ResponseResult;
import com.qqxhb.springcloud.model.response.ResultCode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeacherResult extends ResponseResult {
	Teacher teacher;
    public TeacherResult(ResultCode resultCode,Teacher teacher) {
        super(resultCode);
        this.teacher = teacher;
    }
}