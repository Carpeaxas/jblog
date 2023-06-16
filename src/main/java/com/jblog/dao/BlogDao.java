package com.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;


@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	 public void blogInsert(BlogVo blogVo) {
		 System.out.println("BlogDao.blogInsert()");
		 sqlSession.insert("blog.insert",blogVo); 
	 }
	 
	
	public BlogVo select(String id) {
		System.out.println("BlogDao.select()");
		System.out.println(id);
		
		BlogVo blogVo = sqlSession.selectOne("blog.select", id);
		System.out.println(blogVo); 
		return blogVo;
	}
	
	public void update(BlogVo blogVo) {
		System.out.println("BlogDao.update()");
		System.out.println(blogVo);
		sqlSession.update("blog.update", blogVo);
	}
	
}
