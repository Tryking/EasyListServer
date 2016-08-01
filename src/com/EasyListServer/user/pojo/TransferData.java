package com.EasyListServer.user.pojo;

/**
 * 修改完数据之后传过来的bean
 * @author Administrator
 *
 */
public class TransferData {
    //用户memberId
    private String memberId;
    //事件日期
    private String date;
    //开始时间组成的数组，每个时间之间用一个","隔开
    private String startTimes;
    //结束时间组成的数组，每个时间之间用一个","隔开
    private String endTimes;
    //时间类型组成的数组，每个时间类型之间用一个","隔开
    private String eventTypes;
    //具体的事件组成的数组，每个事件之间用一个"#^@"隔开
    private String specificEvents;

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

	public String getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(String startTimes) {
        this.startTimes = startTimes;
    }

    public String getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(String endTimes) {
        this.endTimes = endTimes;
    }

    public String getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(String eventTypes) {
        this.eventTypes = eventTypes;
    }

    public String getSpecificEvents() {
        return specificEvents;
    }

    public void setSpecificEvents(String specificEvents) {
        this.specificEvents = specificEvents;
    }

	@Override
	public String toString() {
		return "TransferData [memberId=" + memberId + ", date=" + date
				+ ", startTimes=" + startTimes + ", endTimes=" + endTimes
				+ ", eventTypes=" + eventTypes + ", specificEvents="
				+ specificEvents + "]";
	}
}
