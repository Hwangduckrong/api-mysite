package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/users")
	public int joinPerson(@RequestBody UserVo userVo) {
		System.out.println("Joinperson");
		int count = userService.exeJoin(userVo);

		return count;
	}

	@PostMapping("/api/users/login")
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {
		System.out.println("UserController.login");

		UserVo authUser = userService.exeLogin(userVo);
		System.out.println(authUser);

		// 토큰을 만들고 응답문서의 헤더에 토큰을 붙여서 보낸다

		if (authUser != null) {
			JwtUtil.createTokenAndSetHeader(response, "" + authUser.getNo());
			return JsonResult.success(authUser);
		} else {
			return JsonResult.fail("로그인 실패");
		}

	}

}
