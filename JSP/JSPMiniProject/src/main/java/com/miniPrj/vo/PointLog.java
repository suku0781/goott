package com.miniPrj.vo;

import java.sql.Date;

public class PointLog {
	private int id;
	private Date when;
	private String why;
	private int howMuch;
	private String who;
	
	public PointLog(int id, Date when, String why, int howMuch, String who) {
		super();
		this.id = id;
		this.when = when;
		this.why = why;
		this.howMuch = howMuch;
		this.who = who;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public String getWhy() {
		return why;
	}
	public void setWhy(String why) {
		this.why = why;
	}
	public int getHowMuch() {
		return howMuch;
	}
	public void setHowMuch(int howMuch) {
		this.howMuch = howMuch;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	
	@Override
	public String toString() {
		return "PointLog [id=" + id + ", when=" + when + ", why=" + why + ", howMuch=" + howMuch + ", who=" + who + "]";
	}
	
	
	
}
