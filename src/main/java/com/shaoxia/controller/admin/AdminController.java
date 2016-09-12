package com.shaoxia.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("admin")
@Controller
public class AdminController {

	/**
	 * 通用跳转方法
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/{page}.html",method=RequestMethod.GET)
	public ModelAndView toPage(@PathVariable("page")String page,String type){
		ModelAndView mav=new ModelAndView(page);
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
