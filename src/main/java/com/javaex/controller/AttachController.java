package com.javaex.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.util.JsonResult;

@RestController
public class AttachController {
	
	@PostMapping("api/attachs")
	public JsonResult form() {
		System.out.println("AttachController.form()");
		
		return JsonResult.success("");
	}
}
