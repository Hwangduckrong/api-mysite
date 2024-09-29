package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// 회원 가입
	@PostMapping("/api/users")
	public int joinPerson(@RequestBody UserVo userVo) {
		System.out.println("Joinperson");
		int count = userService.exeJoin(userVo);
		return count;
	}

	// 로그인
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

	// 회원정보 수정폼
	@GetMapping("/api/users/me")
	public JsonResult modifyForm(HttpServletRequest request) {
		System.out.println("userController.editForm()");
		int no = JwtUtil.getNoFromHeader(request);
		System.out.println(no);

		if (no != -1) {
			// 정상
			UserVo userVo = userService.exeEditForm(no);
			return JsonResult.success(userVo);
		} else {
			// 토큰이 없거나(로그인 상태 아님), 변조된 경우
			return JsonResult.fail("토큰X, 비로그인, 변조");
		}
	}

	/* 회원정보수정 */
	@PutMapping("/api/users/me")
	public JsonResult editUser(@RequestBody UserVo userVo, HttpServletRequest request) {
		System.out.println("UserController.editUser()");
		
		int no = JwtUtil.getNoFromHeader(request);
		if(no != -1) { //토큰이 정상일때
			userVo.setNo(no);
			System.out.println(userVo);
			int count = userService.exeEditUser(userVo);
			System.out.println(count);
			if(count == 1) {
				//정상적으로 수정되었을때
				userVo.setPassword(null);
				userVo.setGender(null);
				return JsonResult.success(userVo);
			}else {
				return JsonResult.fail("1");
			}

			
		}else {//토큰이 비정상일때
			return JsonResult.fail("토큰X, 비로그인, 변조");
		}
		
	}
	@GetMapping("api/user/duplicate")
	public boolean duplicate(@RequestParam(value = "id") String id) {
		System.out.println("UserController.idCheck()");
		boolean can = userService.exeDuplicate(id);
		System.out.println(id);
		return can;
	}

	
	
	
	
}