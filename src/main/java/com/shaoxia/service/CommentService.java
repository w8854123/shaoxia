package com.shaoxia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaoxia.bean.CommentData;
import com.shaoxia.bean.DataTablesResult;
import com.shaoxia.mapper.CommentDataMapper;
import com.shaoxia.pojo.ArticleComment;

@Service
public class CommentService extends BaseService<ArticleComment>{

	public static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

	@Autowired
	private CommentDataMapper commentDataMapper;
	
	/**
	 * 查询所有非垃圾评论
	 * @return
	 */
	public DataTablesResult<ArticleComment> queryAllComment() {
//		List<ArticleComment> list=super.queryAll();
		ArticleComment param=new ArticleComment();
		param.setCommentSpam(0);
		List<ArticleComment> list=super.queryListByWhere(param);
		DataTablesResult<ArticleComment> dtResult = new DataTablesResult<ArticleComment>();
		dtResult.setData(list);
		return dtResult;
	}

	/**
	 * 查询所有待审评论
	 * @return
	 */
	public DataTablesResult<ArticleComment> queryCommentOfAudit() {
		ArticleComment param=new ArticleComment();
		param.setCommentAudit(0);
		param.setCommentSpam(0);
		List<ArticleComment> list=super.queryListByWhere(param);
		DataTablesResult<ArticleComment> dtResult = new DataTablesResult<ArticleComment>();
		dtResult.setData(list);
		return dtResult;
	}

	/**
	 * 查询所有批准评论
	 * @return
	 */
	public DataTablesResult<ArticleComment> queryCommentOfApproval() {
		ArticleComment param=new ArticleComment();
		param.setCommentAudit(1);
		param.setCommentSpam(0);
		List<ArticleComment> list=super.queryListByWhere(param);
		DataTablesResult<ArticleComment> dtResult = new DataTablesResult<ArticleComment>();
		dtResult.setData(list);
		return dtResult;
	}

	/**
	 * 查询所有垃圾评论
	 * @return
	 */
	public DataTablesResult<ArticleComment> queryCommentOfSpam() {
		ArticleComment param=new ArticleComment();
		param.setCommentSpam(1);
		List<ArticleComment> list=super.queryListByWhere(param);
		DataTablesResult<ArticleComment> dtResult = new DataTablesResult<ArticleComment>();
		dtResult.setData(list);
		return dtResult;
	}

	/**
	 * 统计各项评论数
	 * @return
	 */
	public CommentData queryCommentCount() {
		CommentData result=commentDataMapper.selectCountComment();
		return result;
	}

	/**
	 * 根据id更新评论
	 * @param commentId
	 * @param articleComment
	 */
	public CommentData updateCommentById(Long commentId, ArticleComment articleComment) {
		
		articleComment.setCommentId(commentId);
		super.updateByIdSelective(articleComment);
		
		return queryCommentCount();
	}

	/**
	 * 根据id删除评论
	 * @param commentId
	 * @return
	 */
	public CommentData deleteCommentById(Long commentId) {
		super.deleteById(commentId);
		return queryCommentCount();
	}
	
	
}
