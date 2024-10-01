package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**") // 경로
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("http://localhost:3000","http://localhost:9000")// 아이피자리,이 아이피만
				.allowedHeaders("*") // 모든 요청해더
				.exposedHeaders("Authorization")// 노출시킬헤더
				.allowCredentials(true); // 쿠키허용

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String saveDir;

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("linux")) {
			System.out.println("리눅스");
			/*saveDir = "/home/ec2-user/upload/";*/
			saveDir="/app/upload/";
		} else {
			System.out.println("윈도우");
			saveDir = "C:\\javaStudy\\upload\\";
		}

		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:"+saveDir);//설정 오류 file:C\\로 가야하는 거다  fileC로 하지마라.
	}
}