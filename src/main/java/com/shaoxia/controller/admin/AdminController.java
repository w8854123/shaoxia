package com.shaoxia.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaoxia.bean.CommentData;
import com.shaoxia.service.CommentService;

@RequestMapping("admin")
@Controller
public class AdminController {

	@Autowired
	private CommentService commentService;
	
	/**
	 * 通用跳转方法
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/{page}.html",method=RequestMethod.GET)
	public ModelAndView toPage(@PathVariable("page")String page,String type){
		ModelAndView mav=new ModelAndView(page);
		CommentData commentData=commentService.queryCommentCount();
		mav.addObject("commentCount", commentData);
//		if(type!=null){
//			try {
//				type=new String(type.getBytes(),"UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
			mav.addObject("type", type);
//		}
		return mav;
	}
}
