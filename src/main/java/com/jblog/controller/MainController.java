package com.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	/*
	 * @RequestMapping(value = "/main", method = { RequestMethod.GET ,
	 * RequestMethod.POST}) public String main() {
	 * System.out.println("mainController.main()"); return "/blog/blog-main"; }
	 */
	
	@RequestMapping(value ="/" ,method= {RequestMethod.GET , RequestMethod.POST})
	public String index() {
		System.out.println("mainController.main()");
		return "/main/index";
	}
	
	
	@RequestMapping(value ="/hello")
	public String hello() {
		System.out.println("Hello");
		return "/hello";
	}

}
