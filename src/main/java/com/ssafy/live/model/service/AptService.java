package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.live.model.dto.Apt;
import com.ssafy.live.model.dto.Interest;

public interface AptService {
	// 아파트 매매 정보 조회
	List<Apt> listApt(Map<String, String> map) throws SQLException;
	// 관심매물 등록
	void registerInterest(String userId, String aptCode) throws SQLException;
	// 관심매물 확인
	List<Interest> viewInterest(String userId) throws SQLException;
	// 관심매물 삭제
	void deleteInterest(String aptCode) throws SQLException; 
}
