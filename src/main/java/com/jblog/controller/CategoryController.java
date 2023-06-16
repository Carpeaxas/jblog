package com.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jblog.service.BlogService;
import com.jblog.service.CategoryService;
import com.jblog.vo.CategoryVo;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/{id}/admin/cate")
	public String cateMain(@PathVariable("id")String id , Model model) {
		System.out.println("CategoryController.cateMain()");
		
		model.addAttribute("userVo",blogService.user(id));
		model.addAttribute("blogVo",blogService.blog(id));
		categoryService.view();
		
		return"/blog/admin/blog-admin-cate";
	}
	
	@RequestMapping(value = "/{id}/admin/insert")
	public String insertCate(@RequestBody CategoryVo categoryVo) {
			categoryService.insertCate(categoryVo);
		return"";
	}
}
