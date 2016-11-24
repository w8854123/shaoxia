package com.shaoxia.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shaoxia.bean.DataTablesResult;
import com.shaoxia.pojo.Media;
import com.shaoxia.service.MediaService;

@RequestMapping("admin/media")
@Controller
public class MediaController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);

	@Autowired
	private MediaService mediaService;
	
	/**
	 * 新增
	 * @param media 
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.PUT)
	public ResponseEntity<Void> addMedia(Media media){
		try {
			mediaService.addMedia(media);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			LOGGER.info("输入参数： media = {} ",media);
			LOGGER.error("新增多媒体异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
	}
	
	/**
	 * 查询所有没有删除的多媒体资源
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public ResponseEntity<DataTablesResult<Media>> queryNormalAllMedia(){
		try {
			DataTablesResult<Media> dtResult=mediaService.queryNormalAllMedia();
			return ResponseEntity.ok(dtResult); //200
		} catch (Exception e) {
			LOGGER.error("查询所有非删除多媒体异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); //500
	}
}
