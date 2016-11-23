package com.shaoxia.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	 * 上传文件到七牛云
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value = "/qiniu", method = RequestMethod.PUT)
	public ResponseEntity<Void> uploadQiniu(@RequestParam("file")String filePath){
		try {
			mediaService.uploadQiniu(filePath);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			LOGGER.info("文件路径： filePath = {} ",filePath);
			LOGGER.error("上传七牛云发生异常:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
