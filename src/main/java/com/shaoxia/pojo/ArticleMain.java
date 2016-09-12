package com.shaoxia.pojo;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="shaoxia_article_main")
public class ArticleMain {
	
	@Id
	private String articleId;
	//文章作者
	private String articleAuthor;
	//文章标题
	private String articleTitle;
	//文章标签
	private String articleTags;
	//文章摘要
	private String articleAbstract;
	//文章评论数量
	private Integer articleCommentCount;
	//文章浏览量
	private Integer articleViewCount;
	//文章点赞量
	private Integer goodPointCount;
	//文章置顶，0：不置顶，1：置顶
	private Integer putTop;
	//是否允许评论：0：允许，1：不允许
	private Integer commentType;
	//是否发布：0：发布，1：存草稿箱
	private Integer publish;
	
	private Date created;
	
	private Date updated;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleTags() {
		return articleTags;
	}

	public void setArticleTags(String articleTags) {
		this.articleTags = articleTags;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public Integer getArticleCommentCount() {
		return articleCommentCount;
	}

	public void setArticleCommentCount(Integer articleCommentCount) {
		this.articleCommentCount = articleCommentCount;
	}

	public Integer getArticleViewCount() {
		return articleViewCount;
	}

	public void setArticleViewCount(Integer articleViewCount) {
		this.articleViewCount = articleViewCount;
	}

	public Integer getGoodPointCount() {
		return goodPointCount;
	}

	public void setGoodPointCount(Integer goodPointCount) {
		this.goodPointCount = goodPointCount;
	}

	public Integer getPutTop() {
		return putTop;
	}

	public void setPutTop(Integer putTop) {
		this.putTop = putTop;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
