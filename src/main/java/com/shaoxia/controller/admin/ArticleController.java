package com.shaoxia.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shaoxia.pojo.ArticleMain;
import com.shaoxia.service.ArticleService;

@RequestMapping("admin/article")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insertArticle(ArticleMain articleMain,@RequestParam("articleContent")String content){
		try {
			if(articleService.insertArticle(articleMain,content)){
				return ResponseEntity.status(HttpStatus.CREATED).build(); //201 创建成功
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //400 没有创建成功
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500 服务器错误
	}
}
