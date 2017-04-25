package org.test.beans;

import java.util.Date;

public class Report {
	
	private int id;
	private Date startDate;
	private Date endDate;
	private String performer;
	private String activity;
	
	public Report(int id, Date startDate, Date endDate, String performer, String activity) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.performer = performer;
		this.activity = activity;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	

}

