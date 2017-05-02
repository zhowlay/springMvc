package com.titan.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/web")
public class MvcController {
	@RequestMapping("/hello")
    public String hello(){        
        return "hello";
    }
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(HttpServletRequest req) throws Exception{
	    MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
	    MultipartFile file = mreq.getFile("file");
	    String fileName = file.getOriginalFilename();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");        
	    FileOutputStream fos = new FileOutputStream(req.getSession().getServletContext().getRealPath("/")+
	            "WEB-INF/upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
	    fos.write(file.getBytes());
	    fos.flush();
	    fos.close();
	     
	    return "hello";
	}
	    @RequestMapping("/greeting")
	    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
	        model.addAttribute("name", name);
	        return "greeting";
	    }
}
