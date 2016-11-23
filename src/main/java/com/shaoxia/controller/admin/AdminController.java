package com.shaoxia.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaoxia.bean.CommentData;
import com.shaoxia.pojo.Options;
import com.shaoxia.service.CommentService;
import com.shaoxia.service.MediaService;
import com.shaoxia.service.OptionsService;

@RequestMapping("admin")
@Controller
public class AdminController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private OptionsService optionsService;
	
	/**
	 * 通用跳转方法
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/{page}.html",method=RequestMethod.GET)
	public ModelAndView toPage(@PathVariable("page")String page){
		ModelAndView mav=new ModelAndView(page);
		if("admin-index".equals(page)){
			CommentData commentData=commentService.queryCommentCount();
			mav.addObject("commentCount", commentData);
		}
		if("uploadFile".equals(page)){
			List<Options> optionList=optionsService.queryAll();
		    String qiniuHOST = "";
			if(optionList!=null && optionList.size()>0){
		    	for(Options opt:optionList){
		    		if(MediaService.QINIUHOST.equals(opt.getOptionName())){
		    			qiniuHOST=opt.getOptionValue();
		    			break;
		    		}
		    	}
		    }
			mav.addObject("qiniuHOST",qiniuHOST);
		}
		return mav;
	}
}
