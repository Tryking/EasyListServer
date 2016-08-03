package com.EasyListServer.user.service;

import java.util.List;

import com.EasyListServer.user.pojo.ChangeDataReturnBean;
import com.EasyListServer.user.pojo.Event;
import com.EasyListServer.user.pojo.LoginReturnBean;
import com.EasyListServer.user.pojo.TransferData;
import com.EasyListServer.user.pojo.User;

public interface UserService {
	
	/**
	 * ��¼չʾ��ҳ
	 * @param user ��¼ʱҳ�洫�ݹ�����user
	 * @return LoginReturnBean
	 */
	LoginReturnBean loginAndShow(User user);
	
	 /**
	  * �޸ĵ��¼���¼
	  * @param transferData
	  * @return ChangeDataReturnBean
	 * @throws Exception 
	  */
	ChangeDataReturnBean changeData(TransferData transferData);
	
	/**
	 * ��ȡ�û�ĳһ����¼�
	 * @param menberId
	 * @param date
	 * @return
	 */
	List<Event> getEventByDate(String memberId, String date);	
}
