package com.qqxhb.springcloud.domain.course.request;

import com.qqxhb.springcloud.model.request.RequestData;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class CourseListRequest extends RequestData {
    //公司Id
    private String companyId;
}
