package com.EasyListServer.user.pojo;


import java.util.List;

/**
 * Created by 26011 on 2016/8/1.
 */
public class ViewMonthReturnBean extends BaseBean{
    private String memberId;
    private String month;
    //List�е�����Ϊ�������У�����29�ŵģ�28�ŵģ�27�ŵ�.....
    private List<DayEvent> monthEvents;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<DayEvent> getMonthEvents() {
        return monthEvents;
    }

    public void setMonthEvents(List<DayEvent> monthEvents) {
        this.monthEvents = monthEvents;
    }
}
