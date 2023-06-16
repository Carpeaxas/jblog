package com.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	private SqlSession sqlSession;
	
	public void viewSelect() {
		System.out.println("categoryDao.viewSelect()");
		
	}
	public void insertCate(CategoryVo categoryVo) {
		sqlSession.insert("cate.insert",categoryVo);
	}
	
}
