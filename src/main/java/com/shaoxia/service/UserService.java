package com.shaoxia.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shaoxia.mapper.UserMapper;
import com.shaoxia.pojo.User;
import com.shaoxia.util.CookieUtils;

@Service
public class UserService {

	@Value("${TIAOXIAOPI_TICKET_COOKIE}")
	private String TIAOXIAOPI_TICKET_COOKIE;
	
	@Autowired
	private UserMapper userMapper;
	
	public String getTIAOXIAOPI_TICKET_COOKIE() {
		return TIAOXIAOPI_TICKET_COOKIE;
	}
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user,Integer remember,HttpServletRequest request,HttpServletResponse response) {
		String password=DigestUtils.md5Hex(user.getPassword());//用户输入的密码
		//根据用户输入的用户名查询用户数据
		User param=new User();
		param.setUsername(user.getUsername());
		User databaseUser=userMapper.selectOne(param);
		//判断
		//用户名不存在
		if(databaseUser!=null){
			if(StringUtils.equals(databaseUser.getPassword(), password)){
				// 获取ticket
				String ticket = DigestUtils.md5Hex(databaseUser.getUsername() + System.currentTimeMillis());
				databaseUser.setTicket(ticket);
				//更新数据库中的ticket
				userMapper.updateByPrimaryKeySelective(databaseUser);//根据主键id更新数据，不更新为null的字段
				//将ticket放入cookie中
				if(remember!=null && remember==0){//记住登录状态
					//保存3天
					CookieUtils.setCookie(request, response, TIAOXIAOPI_TICKET_COOKIE, ticket, 60*60*24*3, true);
				}else{
					//不记住登录状态,关闭浏览器就失效
					CookieUtils.setCookie(request, response, TIAOXIAOPI_TICKET_COOKIE, ticket, true);
				}
				return databaseUser;
			}
		}
		return null;
	}

	/**
	 * 根据ticket查询用户
	 * @param ticket
	 * @return
	 */
	public User queryUserByTicket(String ticket) {
		User param=new User();
		param.setTicket(ticket);
		User userInfo=userMapper.selectOne(param);
		return userInfo;
	}
	
}
