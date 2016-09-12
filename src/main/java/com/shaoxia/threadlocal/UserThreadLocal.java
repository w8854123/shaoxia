package com.shaoxia.threadlocal;

import com.shaoxia.pojo.User;

public class UserThreadLocal {

	//定义一个ThreadLocal存放User
	private static ThreadLocal<User> threadLocal=new ThreadLocal<User>();
	
	/**
	 * 将user放入ThreadLocal
	 * @param user
	 */
	public static void set(User user){
		threadLocal.set(user);
	}
	
	/**
	 * 从ThreadLocal中获取user
	 * @return
	 */
	public static User get(){
		return threadLocal.get();
	}
	
    /** 
     * 移除 
     */  
    public static void remove(){  
    	threadLocal.remove();  
    }  
}
