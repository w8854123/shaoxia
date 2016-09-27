package com.shaoxia.pojo;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="shaoxia_article_comment")
public class ArticleComment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long commentId;
	//文章主表id
	private String articleId;
	//文章标题
	private String articleTitle;
	//评论内容
	private String commentContent;
	//评论时间
	private Date commentDate;
	//评论者邮箱
	private String commentEmail;
	//评论者昵称
	private String commentName;
	//评论者qq
	private Long commentQq;
	//评论者网站
	private String commentUrl;
	//审核 0：待审，1：批准
	private Integer commentAudit;
	//垃圾评论 0：正常，1：垃圾
	private Integer commentSpam;
	//类型，0：根，1：回复
	private Integer commentType;
	//父评论id
	private Long originalCommentId;
	//父昵称
	private String originalCommentName;
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentEmail() {
		return commentEmail;
	}
	public void setCommentEmail(String commentEmail) {
		this.commentEmail = commentEmail;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public Long getCommentQq() {
		return commentQq;
	}
	public void setCommentQq(Long commentQq) {
		this.commentQq = commentQq;
	}
	public String getCommentUrl() {
		return commentUrl;
	}
	public void setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
	}
	public Integer getCommentType() {
		return commentType;
	}
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}
	public Long getOriginalCommentId() {
		return originalCommentId;
	}
	public void setOriginalCommentId(Long originalCommentId) {
		this.originalCommentId = originalCommentId;
	}
	public String getOriginalCommentName() {
		return originalCommentName;
	}
	public void setOriginalCommentName(String originalCommentName) {
		this.originalCommentName = originalCommentName;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public Integer getCommentAudit() {
		return commentAudit;
	}
	public void setCommentAudit(Integer commentAudit) {
		this.commentAudit = commentAudit;
	}
	public Integer getCommentSpam() {
		return commentSpam;
	}
	public void setCommentSpam(Integer commentSpam) {
		this.commentSpam = commentSpam;
	}
	
}
