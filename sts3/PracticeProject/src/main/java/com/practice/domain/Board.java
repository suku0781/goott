package com.practice.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Board {
	private String no;
	private String writter;
	private String title;
	private Date postDate;
	private String content;
	private int readCount;
	private int likeCount;
	private int ref;
	private int step;
	private int refOrder;
	private String isDelete;
}
