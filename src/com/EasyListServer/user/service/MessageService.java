package com.EasyListServer.user.service;

import com.EasyListServer.user.pojo.AppVersionReturnBean;
import com.EasyListServer.user.pojo.BaseBean;
import com.EasyListServer.user.pojo.ShareUrlRetrunBean;

public interface MessageService {
	
	/**
	 * ��ȡ�汾��Ϣ
	 * @param deviceType
	 * @param appName
	 * @return
	 */
	AppVersionReturnBean appVersion(String deviceType, String appName);

	/**
	 * �޸��û���Ϣ
	 * @param memberId
	 * @param type
	 * @param param
	 * @return
	 */
	BaseBean updateParam(String memberId, String type, String param);

	/**
	 * �������� 
	 * @param memberId
	 * @return
	 */
	ShareUrlRetrunBean getShareUrl(String memberId);
}
