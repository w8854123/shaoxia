package com.shaoxia.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;
import com.shaoxia.bean.DataTablesResult;
import com.shaoxia.mapper.MediaMapper;
import com.shaoxia.pojo.Media;
import com.shaoxia.pojo.Options;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class MediaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediaService.class);
	
	public static final String QINIUACCESSKEY="qiniuAccessKey";
	public static final String QINIUSECRETKEY="qiniuSecretKey";
	public static final String QINIUHOST="qiniuHOST";
	public static final String QINIUBUCKET="qiniubucket";

	//文件类型后缀
	public static final String[] PICTURESUFFIX={"jpg","png","gif","bmp","jpeg"};
	public static final String[] VIDEOSUFFIX={"mp4","mkv","rmvb","rm","avi","wmv","3gp","mov","flv"};
	public static final String[] AUDIOSUFFIX={"mp3","flac","ape","wav","aac"};

	//状态
	private static final int NORMAL=0;  //正常
	private static final int DELETE=-1;  //删除

	@Autowired
	private MediaMapper mediaMapper;
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
	    			qiNiuOptMap.put(QINIUACCESSKEY, opt.getOptionValue().trim());
	    			continue;
	    		}
	    		if(QINIUSECRETKEY.equals(opt.getOptionName())){
	    			qiNiuOptMap.put(QINIUSECRETKEY, opt.getOptionValue().trim());
	    			continue;
	    		}
	    		if(QINIUBUCKET.equals(opt.getOptionName())){
	    			qiNiuOptMap.put(QINIUBUCKET, opt.getOptionValue().trim());
	    			continue;
	    		}
	    		if(QINIUHOST.equals(opt.getOptionName())){
	    			qiNiuOptMap.put(QINIUHOST, opt.getOptionValue().trim());
	    			continue;
	    		}
	    	}
	    }
		return qiNiuOptMap;
	}

	/**
	 * 查询所有没有删除的多媒体资源
	 * @return
	 */
	public DataTablesResult<Media> queryNormalAllMedia() {
		Example example = new Example(Media.class);
		Criteria criteria=example.createCriteria();
		criteria.andNotEqualTo("status", -1);
		List<Media> mediaList=mediaMapper.selectByExample(example);
		
		DataTablesResult<Media> dtResult=new DataTablesResult<Media>();
		dtResult.setData(mediaList);
		return dtResult;
	}

	/**
	 * 新增
	 * @param media
	 * @throws Exception 
	 */
	public void addMedia(Media media) throws Exception {
		if(media==null){
			throw new Exception("没有实体");
		}
		
		media.setCreated(new Date());
		media.setUpdated(media.getCreated());
		media.setStatus(NORMAL);
		//判断文件类型
		media.setResourceType(checkFileType(media.getResourceSuffix()));

		mediaMapper.insertSelective(media);
	}

	/**
	 * 根据文件后缀判断文件类型
	 * @return
	 */
	public String checkFileType(String suffix){
		for(int i=0;i<PICTURESUFFIX.length;i++){
			if(PICTURESUFFIX[i].equals(suffix.toLowerCase())){
				return "picture";
			}
		}
		for(int i=0;i<VIDEOSUFFIX.length;i++){
			if(VIDEOSUFFIX[i].equals(suffix.toLowerCase())){
				return "video";
			}
		}
		for(int i=0;i<AUDIOSUFFIX.length;i++){
			if(AUDIOSUFFIX[i].equals(suffix.toLowerCase())){
				return "audio";
			}
		}
		return "other";
	}
}
