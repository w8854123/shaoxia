package com.shaoxia.pojo;

import javax.persistence.Table;

@Table(name="shaoxia_article_content")
public class ArticleContent {
	
	//文章主表id
	private String articleId;
	//文章正文
	private String articleContent;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	
}
