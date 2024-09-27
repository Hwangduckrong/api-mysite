package com.javaex.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.util.JsonResult;

@RestController
public class AttachController {
	
	@PostMapping("api/attachs")
	public JsonResult form(@RequestParam("profileImg")MultipartFile profileImg) {
		System.out.println("AttachController.form()");
		System.out.println(profileImg.getOriginalFilename());
		return JsonResult.success("");
	}
}
