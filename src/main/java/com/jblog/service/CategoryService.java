package com.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.CategoryDao;
import com.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	public void view() {
		System.out.println("categoryService.view()");
		categoryDao.viewSelect();
	}
	
	public void insertCate(CategoryVo categoryVo) {
		categoryDao.insertCate(categoryVo);
		
	}

}
