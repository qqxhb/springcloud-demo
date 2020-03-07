package com.qqxhb.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @author Administrator
 * @version 1.0
 **/
@EnableZipkinServer
@SpringBootApplication
public class ZIPkinApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ZIPkinApplication.class, args);
	}
	
}
