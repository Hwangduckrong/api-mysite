package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.TboardVo;

@Repository
public class TboardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<TboardVo> selectList() {
		System.out.println("TboardDao.selectList()");
		
		List<TboardVo> tboardList = sqlSession.selectList("tboard.selectList");
		return tboardList;
	}
	
	public List<TboardVo> selectList2(Map<String, Integer> limitMap) {
		System.out.println("TboardDao.selectList2()");
		
		List<TboardVo> tboardList = sqlSession.selectList("tboard.selectList2", limitMap);
		return tboardList;
	}
	
	public List<TboardVo> selectList3(Map<String, Object> limitMap) {
		System.out.println("TboardDao.selectList3()");
		
		List<TboardVo> tboardList = sqlSession.selectList("tboard.selectList3", limitMap);
		return tboardList;
	}
	
	
	
	/* 전체 글갯수 */
	public int selectTotalCnt() {
		System.out.println("TboardDao.selectTotalCnt()");
		
		int totalCnt = sqlSession.selectOne("tboard.selectTotalCnt");
		return totalCnt;
	}
	
	
	/* 전체 글갯수 */
	public int selectTotalCntKeyword(String keyword) {
		System.out.println("TboardDao.selectTotalCntKeyword()");
		
		int totalCnt = sqlSession.selectOne("tboard.selectTotalCntKeyword", keyword);
		System.out.println(totalCnt);
		return totalCnt;
	}
	
}