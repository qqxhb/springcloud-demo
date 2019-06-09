package com.qqxhb.springcloud.domain.course.response;

import com.qqxhb.springcloud.model.response.ResponseResult;
import com.qqxhb.springcloud.model.response.ResultCode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class AddCourseResult extends ResponseResult {
    public AddCourseResult(ResultCode resultCode,String courseId) {
        super(resultCode);
        this.courseId = courseId;
    }
    private String courseId;

}
