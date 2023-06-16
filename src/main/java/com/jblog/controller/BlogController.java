package com.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	public BlogService blogService;
	
	@RequestMapping(value = "/{id}")
	public String bmain(@PathVariable("id")String id , Model model) {
		model.addAttribute("userVo",blogService.user(id));
		model.addAttribute("blogVo",blogService.blog(id));
		
		System.out.println(blogService.user(id));
		System.out.println(blogService.blog(id));
		
		
		return "/blog/blog-main";
	}
	@RequestMapping(value = "/{id}/admin/basic")
	public String admin(@PathVariable("id")String id , Model model) {
		model.addAttribute("userVo",blogService.user(id));
		model.addAttribute("blogVo",blogService.blog(id));	
		return "/blog/admin/blog-admin-basic";
	}
	
	@RequestMapping(value="/{id}/update")
	public String modify(@PathVariable("id")String id,
			@RequestParam("file") MultipartFile file, Model model ,
			@ModelAttribute BlogVo blogVo) {
		System.out.println("BlogController.modify()");
		System.out.println(blogVo);
		System.out.println(blogVo.getLogoFile());
		if(file.getOriginalFilename()=="") {
			System.out.println("BlogController.modify()");
			blogService.setLogoFile(blogVo);
			
		}else {
			blogService.modify(file , blogVo);
			System.out.println(blogVo);
		}
		return"redirect:/{id}";
		
	}
	
	

}
