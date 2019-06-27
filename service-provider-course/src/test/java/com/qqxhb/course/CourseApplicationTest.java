package com.qqxhb.course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.qqxhb.service.course.CourseApplication;
import com.qqxhb.service.course.client.TeacherClient;

@SpringBootTest(classes = CourseApplication.class)
@RunWith(SpringRunner.class)
public class CourseApplicationTest {
	@Autowired
	RestTemplate restTemplate;

	@Test
	public void testRibbon() {
		for (int i = 0; i < 10; i++) {
			// 通过服务id调用
			ResponseEntity<String> forEntity = restTemplate
					.getForEntity("http://SERVICE-PROVIDER-TEACHER/teacher/test/" + i, String.class);
			String result = forEntity.getBody();
			System.out.println(result);
		}
	}

	@Autowired
	TeacherClient teacherClient;

	@Test
	public void testFeign() {
		for (int i = 0; i < 5; i++) {
			String result = teacherClient.getTeacherTest(i);
			System.out.println(result);
		}
	}
}
