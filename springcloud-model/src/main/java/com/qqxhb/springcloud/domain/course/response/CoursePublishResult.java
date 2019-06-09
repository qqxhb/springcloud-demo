package com.qqxhb.springcloud.domain.course.response;

import com.qqxhb.springcloud.model.response.ResponseResult;
import com.qqxhb.springcloud.model.response.ResultCode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
@NoArgsConstructor
public class CoursePublishResult extends ResponseResult {
    String previewUrl;//页面预览的url，必须得到页面id才可以拼装
    public CoursePublishResult(ResultCode resultCode, String previewUrl) {
        super(resultCode);
        this.previewUrl = previewUrl;
    }
}
