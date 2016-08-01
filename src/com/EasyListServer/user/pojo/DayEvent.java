package com.EasyListServer.user.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by 26011 on 2016/7/25.
 */
public class DayEvent {
	
	private String memberId;
	private String date;
	private List<Event> eventList;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Event> getEventList() {
		return eventList;
	}
	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}
	
}
