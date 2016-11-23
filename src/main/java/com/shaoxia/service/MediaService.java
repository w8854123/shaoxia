package com.shaoxia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;
import com.shaoxia.pojo.Options;

@Service
public class MediaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediaService.class);
	
	public static final String QINIUACCESSKEY="qiniuAccessKey";
	public static final String QINIUSECRETKEY="qiniuSecretKey";
	public static final String QINIUHOST="qiniuHOST";
	public static final String QINIUBUCKET="qiniubucket";
	
	@Autowired
	private OptionsService optionsService;
	
	/**
	 * 获取七牛上传upToken
	 * @return
	 * @throws Exception 
	 */
	public String getUpToken() throws Exception{
		Map<String,String> qiNiuOptMap=this.getQiNiuOption();
		if(qiNiuOptMap.isEmpty()){
			throw new Exception("没有读取到七牛配置文件");
		}
		// 设置好账号的ACCESS_KEY和SECRET_KEY
		String ACCESS_KEY = qiNiuOptMap.get(QINIUACCESSKEY);
		String SECRET_KEY = qiNiuOptMap.get(QINIUSECRETKEY);
		// 要上传的空间
		String bucketname = qiNiuOptMap.get(QINIUBUCKET);
		// 密钥配置
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		return auth.uploadToken(bucketname);
	}
	
	/**
	 * 获取七牛配置信息
	 * @return
	 */
	public Map<String, String> getQiNiuOption(){
		Map<String,String> qiNiuOptMap=new HashMap<String,String>();
		//缓存读取配置文件
		List<Options> optionList=optionsService.queryAll();
		if(optionList!=null && optionList.size()>0){
	    	for(Options opt:optionList){
	    		if(QINIUACCESSKEY.equals(opt.getOptionName())){
	    			qiNiuOptMap.put(QINIUACCESSKEY, opt.getOptionValue());
	    			continue;
	    		}
	    		if(QINIUSECRETKEY.equals(opt.getOptionName())){
	    			qiNiuOptMap.put(QINIUSECRETKEY, opt.getOptionValue());
	    			continue;
	    		}
	    		if(QINIUBUCKET.equals(opt.getOptionName())){
	    			qiNiuOptMap.put(QINIUBUCKET, opt.getOptionValue());
	    			continue;
	    		}
	    		if(QINIUHOST.equals(opt.getOptionName())){
	    			qiNiuOptMap.put(QINIUHOST, opt.getOptionValue());
	    			continue;
	    		}
	    	}
	    }
		return qiNiuOptMap;
	}
	
	/**
	 * 上传文件到七牛云
	 * @param filePath
	 * @throws Exception 
	 */
	public void uploadQiniu(String filePath) throws Exception {
		if(StringUtils.isBlank(filePath)){
			throw new Exception("文件路径为空！");
		}
		//缓存读取配置文件
		List<Options> optionList=optionsService.queryAll();
		//读取七牛配置项
		//设置好账号的ACCESS_KEY和SECRET_KEY
	    String ACCESS_KEY = "";
	    String SECRET_KEY = "";
	    //要上传的空间
	    String bucketname = "";
	    //上传到七牛后保存的文件名
	    String key = UUID.randomUUID().toString();
	    //文件后缀
	    String ext=filePath.split(".")[1];
	    if(optionList!=null && optionList.size()>0){
	    	for(Options opt:optionList){
	    		if(QINIUACCESSKEY.equals(opt.getOptionName())){
	    			ACCESS_KEY=opt.getOptionValue();
	    			continue;
	    		}
	    		if(QINIUSECRETKEY.equals(opt.getOptionName())){
	    			SECRET_KEY=opt.getOptionValue();
	    			continue;
	    		}
	    		if(QINIUBUCKET.equals(opt.getOptionName())){
	    			bucketname=opt.getOptionValue();
	    			continue;
	    		}
	    	}
	    }
	    if("".equals(ACCESS_KEY) || "".equals(SECRET_KEY)){
	    	throw new Exception("没有查询到配置选项");
	    }
	    //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        
	}
	
}
