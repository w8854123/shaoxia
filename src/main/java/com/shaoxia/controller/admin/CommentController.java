package com.shaoxia.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shaoxia.bean.CommentData;
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
	 * 统计各项评论数
	 * @return
	 */
	@RequestMapping(value="/query/count",method=RequestMethod.GET)
	public ResponseEntity<CommentData> queryCommentCount(){
		try {
			CommentData commentData=commentService.queryCommentCount();
			return ResponseEntity.ok(commentData);
		} catch (Exception e) {
			LOGGER.error("统计各项评论数异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 获取所有评论 除垃圾评论外
	 * @return
	 */
	@RequestMapping(value="/query/all",method=RequestMethod.GET)
	public ResponseEntity<DataTablesResult<ArticleComment>> queryAllComment(){
		try {
			DataTablesResult<ArticleComment> dtResult=commentService.queryAllComment();
			return ResponseEntity.ok(dtResult); //200
		} catch (Exception e) {
			LOGGER.error("查询所有非垃圾评论异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); //500
	}
	
	/**
	 * 查询所有待审评论
	 * @return
	 */
	@RequestMapping(value="/query/audit",method=RequestMethod.GET)
	public ResponseEntity<DataTablesResult<ArticleComment>> queryCommentOfAudit(){
		try {
			DataTablesResult<ArticleComment> dtResult=commentService.queryCommentOfAudit();
			return ResponseEntity.ok(dtResult);
		} catch (Exception e) {
			LOGGER.error("查询所有待审评论异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 查询所有批准评论
	 * @return
	 */
	@RequestMapping(value="/query/approval",method=RequestMethod.GET)
	public ResponseEntity<DataTablesResult<ArticleComment>> queryCommentOfApproval(){
		try {
			DataTablesResult<ArticleComment> dtResult=commentService.queryCommentOfApproval();
			return ResponseEntity.ok(dtResult);
		} catch (Exception e) {
			LOGGER.error("查询所有批准评论异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 查询所有垃圾评论
	 * @return
	 */
	@RequestMapping(value="/query/spam",method=RequestMethod.GET)
	public ResponseEntity<DataTablesResult<ArticleComment>> queryCommentOfSpam(){
		try {
			DataTablesResult<ArticleComment> dtResult=commentService.queryCommentOfSpam();
			return ResponseEntity.ok(dtResult);
		} catch (Exception e) {
			LOGGER.error("查询所有垃圾评论异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 根据id更新评论
	 * @param commentId
	 * @param articleComment
	 * @return
	 */
	@RequestMapping(value="/update/{commentId}",method=RequestMethod.PATCH)
	public ResponseEntity<CommentData> updateCommentById(@PathVariable("commentId")Long commentId,ArticleComment articleComment){
		try {
			CommentData commentData=commentService.updateCommentById(commentId,articleComment);
			return ResponseEntity.status(HttpStatus.OK).body(commentData); //200
		} catch (Exception e) {
			LOGGER.info("根据id更新评论异常,输入参数： commentId = {} , articleComment = {} ",commentId,articleComment);
			LOGGER.error("根据id更新评论异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 根据id删除评论
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value="/delete/{commentId}",method=RequestMethod.DELETE)
	public ResponseEntity<CommentData> deleteCommentById(@PathVariable("commentId")Long commentId){
		try {
			CommentData commentData=commentService.deleteCommentById(commentId);
			return ResponseEntity.status(HttpStatus.OK).body(commentData);
		} catch (Exception e) {
			LOGGER.error("根据id删除评论异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
