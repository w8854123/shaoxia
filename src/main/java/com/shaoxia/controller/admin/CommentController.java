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
import com.shaoxia.pojo.ArticleComment;
import com.shaoxia.service.CommentService;

@RequestMapping("admin/comment")
@Controller
public class CommentController {

	public static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 获取所有评论
	 * @return
	 */
	@RequestMapping(value="/query/all",method=RequestMethod.GET)
	public ResponseEntity<DataTablesResult<ArticleComment>> queryAllComment(){
		try {
			DataTablesResult<ArticleComment> dtResult=commentService.queryAllComment();
			return ResponseEntity.ok(dtResult); //200
		} catch (Exception e) {
			LOGGER.error("查询所有文章异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); //500
	}
}
