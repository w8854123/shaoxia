package com.shaoxia.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shaoxia.service.MediaService;

/**
 * 文件上传
 * @author wu885
 *
 */
@Controller
@RequestMapping("/admin/upload")
public class UploadFileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileController.class);
	
	@Autowired
	private MediaService mediaService;
	
	
	/**
	 * 获取七牛上传upToken
	 * @return
	 */
	@RequestMapping(value = "/getUpToken", method = RequestMethod.GET)
	public ResponseEntity<Map<String,String>> getUpToken(){
		try {
			String upToken=mediaService.getUpToken();
			Map<String,String> jsonMap=new HashMap<String,String>();
			jsonMap.put("uptoken", upToken);
			return ResponseEntity.ok(jsonMap);
		} catch (Exception e) {
			LOGGER.error("获取七牛上传upToken异常:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
}
