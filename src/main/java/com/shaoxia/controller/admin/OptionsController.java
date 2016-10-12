package com.shaoxia.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shaoxia.bean.ListOption;
import com.shaoxia.pojo.Options;
import com.shaoxia.service.OptionsService;

/**
 * 设置选项控制器
 * @author wu885
 *
 */
@RequestMapping("admin/options")
@Controller
public class OptionsController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OptionsController.class);

	@Autowired
	private OptionsService optionsService;
	
	/**
	 * 获取所有设置选项
	 * @return
	 */
	@RequestMapping(value="/query/all",method=RequestMethod.GET)
	public ResponseEntity<List<Options>> queryAll(){
		try {
			List<Options> list=optionsService.queryAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			LOGGER.error("获取所有设置选项异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 批量插入设置项
	 * @param listOption
	 * @return
	 */
	@RequestMapping(value="/insertBatch",method=RequestMethod.POST)
	public ResponseEntity<Void> saveBatchOptions(ListOption listOption){
		try {
			optionsService.saveBatchOptions(listOption);
			return ResponseEntity.status(HttpStatus.CREATED).build(); //201
		} catch (Exception e) {
			LOGGER.error("批量插入设置项异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	/**
	 * 批量更新设置选项
	 * @param listOption
	 * @return
	 */
	@RequestMapping(value="/updateBatch",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateOptionsById(ListOption listOption){
		try {
			optionsService.updateOptionsById(listOption);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //204
		} catch (Exception e) {
			LOGGER.error("批量更新设置项异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
