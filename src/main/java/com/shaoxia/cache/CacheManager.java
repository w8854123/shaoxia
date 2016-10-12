package com.shaoxia.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用静态ConcurrentHashMap实现的线程安全的缓存
 * @author wu885
 *
 */
@Component
public class CacheManager {

	public static final Logger LOGGER = LoggerFactory.getLogger(CacheManager.class);
	
	//ConcurrentHashMap是线程安全的，HashMap不是线程安全的
	private static final Map<String, Object> CACHEMAP=new ConcurrentHashMap<String, Object>();

	public static Map<String, Object> getCachemap() {
		return CACHEMAP;
	}

	/**
	 * 清空缓存
	 */
	public void clearCache(){
		CACHEMAP.clear();
	}
	
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @return 
	 */
	public boolean putCache(String key,Object value){
		if(key!=null && !CACHEMAP.containsKey(key)){
			CACHEMAP.put(key, value);
			LOGGER.info("缓存设置成功： key = {} , value = {} ",key,value);
			return true;
		}
		LOGGER.error("key为null或缓存中已存在这个key -------- key = "+key);
		return false;
	}
	
	/**
	 * 根据key获取值
	 * @param key
	 * @return
	 */
	public Object getValueByKey(String key){
		if(key!=null){
			return CACHEMAP.get(key);
		}
		return null;
	}
	
	/**
	 * 根据key更新缓存数据
	 * @param key
	 * @param value
	 */
	public boolean updateValueByKey(String key,Object value){
		removeCacheByKey(key);
		return putCache(key,value);
	}
	
	/**
	 * 根据key删除缓存数据
	 * @param key
	 */
	public boolean removeCacheByKey(String key){
		if(key!=null && CACHEMAP.containsKey(key)){
			CACHEMAP.remove(key);
			LOGGER.info("缓存删除成功： key = {} ",key);
			return true;
		}
		LOGGER.error("缓存没有被删除可能不存在这个key或key为null -------- key = "+key);
		return false;
	}
	
	/**
	 * 获取缓存大小
	 * @return
	 */
	public int getCacheSize(){
		return CACHEMAP.size();
	}
}
