package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.TboardService;
import com.javaex.util.JsonResult;
import com.javaex.vo.TboardVo;

@RestController
public class TboardController {

	@Autowired
	private TboardService tboardService;

	// 리스트 가져오기

	@GetMapping("/api/tboards")
	public JsonResult getList() {

		List<TboardVo> tboardList = tboardService.exeList();

		return JsonResult.success(tboardList);
	}

	@GetMapping("/list2")
	public ResponseEntity<Map<String, Object>> list2(
			@RequestParam(value = "crtpage", required = false, defaultValue = "1") int crtPage) {
		System.out.println("TboardRestController.list2()");

		// Service에서 페이징 처리된 게시글 리스트를 가져옴
		Map<String, Object> pMap = tboardService.exeList2(crtPage);
		System.out.println(pMap);

		// ResponseEntity로 JSON 데이터와 상태 코드 반환
		return ResponseEntity.ok(pMap);
	}
}
