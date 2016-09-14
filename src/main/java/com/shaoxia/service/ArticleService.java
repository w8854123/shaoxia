package com.shaoxia.service;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaoxia.mapper.ArticleCommentMapper;
import com.shaoxia.mapper.ArticleContentMapper;
import com.shaoxia.mapper.ArticleMainMapper;
import com.shaoxia.pojo.ArticleComment;
import com.shaoxia.pojo.ArticleContent;
import com.shaoxia.pojo.ArticleMain;
import com.shaoxia.threadlocal.UserThreadLocal;

@Service
public class ArticleService {

	public static final Logger LOGGER=LoggerFactory.getLogger(ArticleService.class);
	
	@Autowired
	private ArticleCommentMapper articleCommentMapper;
	@Autowired
	private ArticleContentMapper articleContentMapper;
	@Autowired
	private ArticleMainMapper articleMainMapper;
	
	public void save(){
		ArticleMain articleMain=new ArticleMain();
		ArticleContent articleContent=new ArticleContent();
		ArticleComment articleComment=new ArticleComment();
		String uuid=UUID.randomUUID().toString();
		Date time=new Date();
		
		articleMain.setArticleId(uuid);
		articleContent.setArticleId(uuid);
		articleComment.setArticleId(uuid);
		
		articleMain.setArticleTitle("标题1");
		articleMain.setArticleAuthor("作者1");
		articleMain.setArticleTags("标签1");
		articleMain.setArticleAbstract("摘要1");
		articleMain.setArticleCommentCount(0);
		articleMain.setArticleViewCount(0);
		articleMain.setGoodPointCount(0);
		articleMain.setCreated(time);
		articleMain.setUpdated(time);
		
		articleContent.setArticleContent("正文1");
		
		articleComment.setCommentContent("评论内容1");
		articleComment.setCommentDate(time);
		articleComment.setCommentEmail("email-1");
		articleComment.setCommentName("评论昵称1");
		articleComment.setCommentQq(641116388l);
		articleComment.setCommentUrl("http://www.baidu.com");
		
		
		articleMainMapper.insertSelective(articleMain);
		articleContentMapper.insertSelective(articleContent);
		articleCommentMapper.insertSelective(articleComment);
	}

	public boolean insertArticle(ArticleMain articleMain, String content) throws Exception {
		if(articleMain!=null){
			String uuid=UUID.randomUUID().toString();
			Date time=new Date();
			articleMain.setArticleId(uuid);
			articleMain.setCreated(time);
			articleMain.setUpdated(time);
			articleMain.setArticleAuthor(UserThreadLocal.get().getNickname());
			
			ArticleContent articleContent=new ArticleContent();
			articleContent.setArticleId(uuid);
			articleContent.setArticleContent(content);
			
			articleMainMapper.insertSelective(articleMain);
			articleContentMapper.insert(articleContent);
			return true;
		}
		LOGGER.info("没有-ArticleMain-数据！");
		return false;
	}
}
