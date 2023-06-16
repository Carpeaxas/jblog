package com.jblog.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.dao.BlogDao;
import com.jblog.dao.UserDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.UserVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private UserDao userDao;
	
	
	public void blogInsert(BlogVo blogVo) { 
	blogDao.blogInsert(blogVo); 
	}
	 
	
	public UserVo user(String id) {		
		System.out.println("BlogService.user()");
		UserVo userVo = userDao.list(id);
		return userVo;
	}
	public BlogVo blog(String id) {
		System.out.println("BlogService.blog()");
		BlogVo blogVo = blogDao.select(id);
		return blogVo;
	}
	
	public void setLogoFile(BlogVo blogVo) {
		System.out.println("BlogService.setLogoFile()");
		BlogVo vo = new BlogVo(blogVo.getId(),blogVo.getBlogTitle(),"spring-logo.jpg");
		blogDao.update(vo);
		
	}
	
	public void modify(MultipartFile file ,BlogVo blogVo) {
		
		String saveDir = "C:\\javaStudy\\upload";
		
		//원파일 이름
		String orgname = file.getOriginalFilename();
		System.out.println("orgName : " + orgname);
		
		//확장자
		String exName = orgname.substring(orgname.lastIndexOf("."));
		System.out.println(exName);
		
		//저장파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName: " + saveName);
		
		//파일패스
		String filePath = saveDir +"\\"+saveName;
		System.out.println("filePath: " + filePath);
		
		//파일 사이즈
		
		 long fileSize =file.getSize(); 
		 System.out.println("fileSize: " + fileSize);
		 
		
		// 파일 업로드
		
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(fileData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BlogVo vo = new BlogVo(blogVo.getId(),blogVo.getBlogTitle(),orgname);
		System.out.println(vo);
		blogDao.update(vo);
		
		
	}
	
}
