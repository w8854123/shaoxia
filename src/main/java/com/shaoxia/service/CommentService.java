package com.shaoxia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shaoxia.bean.DataTablesResult;
import com.shaoxia.pojo.ArticleComment;

@Service
public class CommentService extends BaseService<ArticleComment>{

	public static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

	/**
	 * 查询所有评论
	 * @return
	 */
	public DataTablesResult<ArticleComment> queryAllComment() {
		List<ArticleComment> list=super.queryAll();
		DataTablesResult<ArticleComment> dtResult = new DataTablesResult<ArticleComment>();
		dtResult.setData(list);
		return dtResult;
	}
	
	
}
