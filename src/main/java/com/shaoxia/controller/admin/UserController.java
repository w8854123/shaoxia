package com.shaoxia.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shaoxia.pojo.User;
import com.shaoxia.service.UserService;

@RequestMapping("admin/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> login(User user,Integer remember,HttpServletRequest request,HttpServletResponse response){
		try {
			User result=userService.login(user,remember,request,response);
			if(result!=null){
				return ResponseEntity.ok(null); //登录成功 200
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //登录失败 404
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //服务器内部错误 500
	}
	
}
