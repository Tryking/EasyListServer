package com.EasyListServer.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.EasyListServer.user.mapper.UserMapper;
import com.EasyListServer.user.pojo.AppVersionReturnBean;
import com.EasyListServer.user.pojo.BaseBean;
import com.EasyListServer.user.pojo.ShareUrlRetrunBean;
import com.EasyListServer.user.pojo.User;
import com.EasyListServer.user.pojo.UserExample;
import com.EasyListServer.user.pojo.UserExample.Criteria;
import com.EasyListServer.user.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService{
	
	@Value("${APPNAME}")
	private String APPNAME;
	@Value("${APPVERSIONNUM}")
	private String APPVERSIONNUM;
	@Value("${APPDOWNLOADPATH}")
	private String APPDOWNLOADPATH;
	@Value("${APPDESCRIBE}")
	private String APPDESCRIBE;
	@Value("${ISCHANGE}")
	private String ISCHANGE;
	@Value("${SHAREURL}")
	private String SHAREURL;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public AppVersionReturnBean appVersion(String deviceType, String appName) {
		
		AppVersionReturnBean appVersionReturnBean = new AppVersionReturnBean();
		
		if(deviceType == null || appName == null){
			appVersionReturnBean.setResult("0");
			appVersionReturnBean.setMsg("����Ϊ��");
			return appVersionReturnBean;
		} else if (!deviceType.equals("android") || !appName.equals("EasyList")) {
			appVersionReturnBean.setResult("0");
			appVersionReturnBean.setMsg("��������");
			return appVersionReturnBean;
		}
		
		appVersionReturnBean.setResult("1");
		appVersionReturnBean.setMsg("��ѯ�ɹ�");
		appVersionReturnBean.setAppName(APPNAME);
		appVersionReturnBean.setAppVersionNum(APPVERSIONNUM);
		appVersionReturnBean.setAppDownloadPath(APPDOWNLOADPATH);
		appVersionReturnBean.setAppDescribe(APPDESCRIBE);
		
		return appVersionReturnBean;
	}

	/**
	 * 1:account
	 * 2:password
	 * 3:gender
	 * 4:phone
	 * 5:signature
	 */
	@Override
	public BaseBean updateParam(String memberId, String type, String param) {
		
		BaseBean baseBean = new BaseBean();
		
		if(memberId==null || type==null){
			baseBean.setResult("0");
			baseBean.setMsg("����Ϊ��");
			return baseBean;
		}
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberId);
		List<User> list = userMapper.selectByExample(example);
		
		if(list==null || list.size()==0){
			baseBean.setResult("0");
			baseBean.setMsg("���޴���");
			return baseBean;
		}
		
		User user = list.get(0);
		
		//�ж�Ҫ�޸ĵ���ʲô
		if(type.equals("1")){
			user.setAccount(param);
		} else if (type.equals("2")) {
			user.setPassword(param);
		} else if (type.equals("3")) {
			if(!param.equals("0") && !param.equals("1")){
				baseBean.setResult("0");
				baseBean.setMsg("�Ա����");
				return baseBean;
			}
			user.setGender(Integer.parseInt(param));
		} else if (type.equals("4")) {
			if(param.length()!=11){
				baseBean.setResult("0");
				baseBean.setMsg("�ֻ����벻��ȷ");
				return baseBean;
			}
			user.setPhone(param);
		} else if (type.equals("5")) {
			user.setSignature(param);
		}
		
		user.setUpdatedtime(new Date());
		
		userMapper.updateByPrimaryKey(user);
		
		baseBean.setResult("1");
		baseBean.setMsg("���³ɹ�");
		return baseBean;
	}

	@Override
	public ShareUrlRetrunBean getShareUrl(String memberId) {
		ShareUrlRetrunBean shareUrlRetrunBean = new ShareUrlRetrunBean();
		
		if(memberId != null && !memberId.equals("")){
			shareUrlRetrunBean.setResult("1");
			shareUrlRetrunBean.setMsg("�ɹ�");
			shareUrlRetrunBean.setIsChange(ISCHANGE);
			shareUrlRetrunBean.setShareUrl(SHAREURL);
			
			return shareUrlRetrunBean;
		}
		
		shareUrlRetrunBean.setResult("0");
		shareUrlRetrunBean.setMsg("����Ϊ��");
		
		return shareUrlRetrunBean;
	}

}
