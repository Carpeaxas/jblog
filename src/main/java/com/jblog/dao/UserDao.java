package com.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVo userVo) {
		
		System.out.println("UserDao.insertUser()");
		int count = sqlSession.insert("user.insert",userVo);
		return count;
		
	}
	
	public UserVo selectUser(String id) {
		System.out.println("UserDao.selectUser()");
		UserVo userVo = sqlSession.selectOne("user.selectUserId" ,id);
		System.out.println(userVo);
		return userVo;
	}
	
	public UserVo login(UserVo userVo) {
		UserVo login = sqlSession.selectOne("user.selectUser",userVo);
		
		return login;
	}
	
	public UserVo list(String id) {
		System.out.println("UserDao.list()");
	 UserVo userVo =sqlSession.selectOne("user.selectList" , id);
	 System.out.println(userVo);
	 return userVo;
	}
		

}
