package com.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.UserDao;
import com.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		System.out.println("UserService.join()");
		
		
		int count = userDao.insertUser(userVo);
		return count;
	}
	
	public boolean idcheck(String id) {
		System.out.println("UserService.idcheck()");
		UserVo userVo = userDao.selectUser(id);
		if(userVo == null) {
			return true;
		}else {
		return false;
		}
	}
	public UserVo login(UserVo userVo) {
		UserVo login =userDao.login(userVo);
		return login;
	}
	
	
}
