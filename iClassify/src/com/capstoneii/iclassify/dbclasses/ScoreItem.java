package com.capstoneii.iclassify.dbclasses;

public class ScoreItem {

	int sid;
	String course;
	String points;
	String sdate;

	public ScoreItem(int sid, String course, String points, String sdate) {
		super();
		this.sid = sid;
		this.course = course;
		this.points = points;
		this.sdate = sdate;
	}

	public ScoreItem(String course, String points, String sdate) {
		super();
		this.course = course;
		this.points = points;
		this.sdate = sdate;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

}

