package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.TboardService;
import com.javaex.util.JsonResult;
import com.javaex.vo.TboardVo;

@RestController
public class TboardController {
	
	@Autowired
	private TboardService tboardService;
	
	//리스트 가져오기
	
	@GetMapping("/api/tboards")
	public JsonResult getList() {
		
		List<TboardVo> tboardList =tboardService.exeList();
		
		return JsonResult.success(tboardList);
	}

}