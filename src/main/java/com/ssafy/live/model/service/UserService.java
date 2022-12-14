package com.ssafy.live.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.live.model.dto.User;

public interface UserService {

	/* 일반 유저 서비스 */
	int idCheck(String userId) throws Exception;

	void joinUser(User user) throws Exception;

	String searchPwd(User user) throws Exception;

	/* 관리자 서비스 */
	List<User> listUser(Map<String, Object> map) throws Exception;

	void updateUser(User user) throws Exception;

	void deleteUser(String userid) throws Exception;

	/* 공통 서비스 */
	User getUser(String userId) throws Exception;

	/* 토큰 사용 */
	User loginUser(User user) throws Exception;

	void saveRefreshToken(String userId, String token);

	void deleteRefreshToken(String userId);

	String getRefreshToken(String userId);
	
	/*이메일 인증*/
	int makeRandomNum();
	
	String joinEmail(String email);

	void sendMail(String setFrom, String toMail, String title, String content);
	
	/*이메일 변경*/
	void updateEmail(User user) throws Exception;

}
