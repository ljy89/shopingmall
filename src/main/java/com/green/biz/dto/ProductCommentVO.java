package com.green.biz.dto;

import java.util.Date;

public class ProductCommentVO {

	private int comment_seq;
	private int pseq;
	private String content;
	private String writer;
	private Date regDate;
	private Date modifyDate;
	public int getComment_seq() {
		return comment_seq;
	}
	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
	}
	public int getPseq() {
		return pseq;
	}
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "ProductCommentVO [comment_seq=" + comment_seq + ", pseq=" + pseq + ", content=" + content + ", writer="
				+ writer + ", regDate=" + regDate + ", modifyDate=" + modifyDate + "]";
	}
	
	
}
