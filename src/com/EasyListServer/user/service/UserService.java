package com.EasyListServer.user.service;

import java.util.List;

import com.EasyListServer.user.pojo.ChangeDataReturnBean;
import com.EasyListServer.user.pojo.DayEvent;
import com.EasyListServer.user.pojo.DayEventReturnBean;
import com.EasyListServer.user.pojo.Event;
import com.EasyListServer.user.pojo.LoginReturnBean;
import com.EasyListServer.user.pojo.TransferData;
import com.EasyListServer.user.pojo.User;
import com.EasyListServer.user.pojo.ViewMonthReturnBean;

public interface UserService {

	/**
	 * ��¼չʾ��ҳ
	 * 
	 * @param user
	 *            ��¼ʱҳ�洫�ݹ�����user
	 * @return LoginReturnBean
	 */
	LoginReturnBean loginAndShow(User user);

	/**
	 * �޸ĵ��¼���¼
	 * 
	 * @param transferData
	 * @return ChangeDataReturnBean
	 * @throws Exception
	 */
	ChangeDataReturnBean changeData(TransferData transferData);

	/**
	 * ��ȡ�û�ĳһ����¼�
	 * 
	 * @param menberId
	 * @param date
	 * @return
	 */
	DayEventReturnBean getEventByDate(String memberId, String date);

	/**
	 * �鿴һ���µļ�¼
	 * 
	 * @param memberId
	 * @param month
	 * @return
	 */
	ViewMonthReturnBean viewMonthData(String memberId, String month);
}
