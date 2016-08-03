package com.EasyListServer.user.service;

import java.util.List;

import com.EasyListServer.user.pojo.ChangeDataReturnBean;
import com.EasyListServer.user.pojo.Event;
import com.EasyListServer.user.pojo.LoginReturnBean;
import com.EasyListServer.user.pojo.TransferData;
import com.EasyListServer.user.pojo.User;

public interface UserService {
	
	/**
	 * 登录展示首页
	 * @param user 登录时页面传递过来的user
	 * @return LoginReturnBean
	 */
	LoginReturnBean loginAndShow(User user);
	
	 /**
	  * 修改的事件记录
	  * @param transferData
	  * @return ChangeDataReturnBean
	 * @throws Exception 
	  */
	ChangeDataReturnBean changeData(TransferData transferData);
	
	/**
	 * 获取用户某一天的事件
	 * @param menberId
	 * @param date
	 * @return
	 */
	List<Event> getEventByDate(String memberId, String date);	
}
