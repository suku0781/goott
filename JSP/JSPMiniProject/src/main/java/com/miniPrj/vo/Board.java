package com.miniPrj.vo;

import java.sql.Timestamp;

public class Board {
	  private int no;
	  private String writter;
	  private String title;
	  private Timestamp postDate;
	  private String content;
	  private int readCount;
	  private int likeCount;
	  private int ref;
	  private int step;
	  private int refOrder;
	  private String isDelete;
	  
	public Board(int no, String writter, String title, Timestamp postDate, String content, int readCount, int likeCount, int ref, int step, int refOrder, String isDelete) {
		super();
		this.no = no;
		this.writter = writter;
		this.title = title;
		this.postDate = postDate;
		this.content = content;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
		this.isDelete = isDelete;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWritter() {
		return writter;
	}

	public void setWritter(String writter) {
		this.writter = writter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getPostDate() {
		return postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getRefOrder() {
		return refOrder;
	}

	public void setRefOrder(int refOrder) {
		this.refOrder = refOrder;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Board [no=" + no + ", writter=" + writter + ", title=" + title + ", postDate=" + postDate + ", content=" + content + ", readCount=" + readCount + ", likeCount=" + likeCount + ", ref=" + ref + ", step=" + step + ", refOrder=" + refOrder + ", isDelete=" + isDelete + "]";
	}
			  
}
