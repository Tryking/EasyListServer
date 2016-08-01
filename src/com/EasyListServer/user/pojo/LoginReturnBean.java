package com.EasyListServer.user.pojo;
/**
 * 登陆完之后返回的bean
 * @author Administrator
 *
 */
public class LoginReturnBean extends BaseBean {
    private boolean isNewAccount;
    private User user;
    private DayEvent dayEvent;
	public boolean isNewAccount() {
		return isNewAccount;
	}
	public void setNewAccount(boolean isNewAccount) {
		this.isNewAccount = isNewAccount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public DayEvent getDayEvent() {
		return dayEvent;
	}
	public void setDayEvent(DayEvent dayEvent) {
		this.dayEvent = dayEvent;
	}
    
    
}
