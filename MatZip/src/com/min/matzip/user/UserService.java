package com.min.matzip.user;

import com.min.matzip.SecurityUtils;
import com.min.matzip.vo.UserVO;

public class UserService {
	
	private UserDAO dao= new UserDAO();

	public int join(UserVO param) {
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String encryptPw = SecurityUtils.getEncrypt(pw, salt);

		param.setUser_pw(encryptPw);
		param.setSalt(salt);

		return dao.join(param);
	}
	
	//result 값  1: 로그인 성공, 2: 아이디없음, 3: 비밀번호 틀림
	public int login(UserVO param) { //param에 넘어오는건 id, pw
		int result = 0;

		UserVO dbResult = dao.selUser(param);
		
		if(dbResult.getI_user() == 0) { //id 없음
			result = 2;
		} else {
			String salt = dbResult.getSalt();
			String encryptPw = SecurityUtils.getEncrypt(param.getUser_pw(), salt);
			
			if(encryptPw.equals(dbResult.getUser_pw())) {
				result = 1;
			} else {
				result = 3;
			}
		}
		
		return result;
	}
}