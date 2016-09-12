package com.shaoxia.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shaoxia.service.ArticleService;

@RequestMapping("admin")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(){
		articleService.save();
		return "success";
	}
}
