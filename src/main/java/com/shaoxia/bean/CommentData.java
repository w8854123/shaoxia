package com.shaoxia.bean;

/**
 * 评论数量统计
 * @author wu885
 *
 */
public class CommentData {

	//非垃圾（全部）评论数
	private Long sum;
	//待审评论数
	private Long sumAudit;
	//批准评论数
	private Long sumApproval;
	//垃圾评论数
	private Long sumSpam;
	
	public Long getSum() {
		return sum;
	}
	public void setSum(Long sum) {
		this.sum = sum;
	}
	public Long getSumAudit() {
		return sumAudit;
	}
	public void setSumAudit(Long sumAudit) {
		this.sumAudit = sumAudit;
	}
	public Long getSumApproval() {
		return sumApproval;
	}
	public void setSumApproval(Long sumApproval) {
		this.sumApproval = sumApproval;
	}
	public Long getSumSpam() {
		return sumSpam;
	}
	public void setSumSpam(Long sumSpam) {
		this.sumSpam = sumSpam;
	}
}
