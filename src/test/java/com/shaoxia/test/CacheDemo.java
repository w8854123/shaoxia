package com.shaoxia.test;

import com.shaoxia.cache.CacheManager;

public class CacheDemo {

	public static void main(String[] args){
		CacheManager cache=new CacheManager();
		System.out.println(cache.getCacheSize());
		cache.putCache("1号", "khaskjjs");
		cache.putCache("2号", "哦搜索");
		cache.putCache("3号", "梵蒂冈地方");
		cache.putCache("4号", "kha于阿斯蒂芬");
		System.out.println(cache.getCacheSize());
		System.out.println(cache.getValueByKey("3号"));
		System.out.println(cache.updateValueByKey("3号", "梵蒂冈地方   -----gai "));
		System.out.println(cache.getValueByKey("3号"));
		System.out.println(cache.putCache("4号", "kha于阿斯蒂芬-------新"));
	}
}
