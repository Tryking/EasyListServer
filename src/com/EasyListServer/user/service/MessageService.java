package com.EasyListServer.user.service;

import com.EasyListServer.user.pojo.AppVersionReturnBean;
import com.EasyListServer.user.pojo.BaseBean;
import com.EasyListServer.user.pojo.ShareUrlRetrunBean;

public interface MessageService {
	
	/**
	 * 获取版本信息
	 * @param deviceType
	 * @param appName
	 * @return
	 */
	AppVersionReturnBean appVersion(String deviceType, String appName);

	/**
	 * 修改用户信息
	 * @param memberId
	 * @param type
	 * @param param
	 * @return
	 */
	BaseBean updateParam(String memberId, String type, String param);

	/**
	 * 分享链接 
	 * @param memberId
	 * @return
	 */
	ShareUrlRetrunBean getShareUrl(String memberId);
}
