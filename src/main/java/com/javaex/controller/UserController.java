package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/users")
	public int joinPerson(@RequestBody UserVo userVo) {
		System.out.println("Joinperson");
		int count =userService.exeJoin(userVo);
		
		return count;
	}
}
