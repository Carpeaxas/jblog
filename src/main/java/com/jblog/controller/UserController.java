package com.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.BlogService;
import com.jblog.service.UserService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.JsonResult;
import com.jblog.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET , RequestMethod.POST})
	public String loginForm() {
		
		System.out.println("userController.loginForm()");
		
		return "/user/loginForm";
	}
	
	
	
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET , RequestMethod.POST})
	public String joinForm() {
		
		System.out.println("userController.joinForm()");
		
		return "/user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/join", method= {RequestMethod.GET , RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo,
						@ModelAttribute BlogVo blogVo) {
		System.out.println("UserController.join()");
		userService.join(userVo);
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그 입니다.");
		blogVo.setLogoFile("spring-logo.jpg");
		blogService.blogInsert(blogVo); 
		
		return "/user/joinSuccess" ;
	}
	
	//아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "/idcheck", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult idcheck(@RequestParam("id") String id) {
		System.out.println("UserController.idcheck()");
		
		   boolean data = userService.idcheck(id);
			JsonResult jsonResult = new JsonResult(); 
			jsonResult.success(data);
			 System.out.println(jsonResult);
		
		return jsonResult;
			
	}
	
	
	//로그인 , 아이디 비밀번호 체크
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo , HttpSession session ){
		System.out.println(userVo);
		UserVo login = userService.login(userVo);

		System.out.println(login);
		
		if (login != null) { // 로그인 성공일때
			System.out.println("로그인성공");
			session.setAttribute("info", login);
			return "redirect:/";
			
		} else { // 로그인 실패일때
			System.out.println("로그인실패");
			return "redirect:/user/loginForm?result=fail";
		}	
	}
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		session.invalidate();
		return"redirect:/";
	}
	

	

}
