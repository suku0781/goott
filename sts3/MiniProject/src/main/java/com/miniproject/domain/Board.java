package com.miniproject.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
	  
	  
}
