package com.EasyListServer.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.EasyListServer.user.pojo.ChangeDataReturnBean;
import com.EasyListServer.user.pojo.Event;
import com.EasyListServer.user.pojo.LoginReturnBean;
import com.EasyListServer.user.pojo.TransferData;
import com.EasyListServer.user.pojo.User;
import com.EasyListServer.user.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class userController {

	@Autowired
	private UserService userService;
	
	/**
	 * ��½֮������ʾȫ��event��ֻ����ʾ�����event
	 * @param user ��¼ʱ��user
	 * @return loginAndShow��json
	 */
	@RequestMapping(value="/login",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String loginAndShow(User user){
		LoginReturnBean loginAndShow = userService.loginAndShow(user);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(loginAndShow);
		return json;
	}
	
	/**
	 * �޸�֮�󷵻ص�����
	 * @param transferData
	 * @return
	 */
	@RequestMapping(value="/changeData",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String changeData(TransferData transferData){

//		transferData.setMemberId("1111");
//		transferData.setDate("20160729");
//		transferData.setStartTimes("1111,111,1");
//		transferData.setEndTimes("1111,111,1");
//		transferData.setEventTypes("1111,111,1");
//		transferData.setSpecificEvents("#^@#^@");
		
		System.out.println("...................");
		System.out.println("transferData:"+transferData);
		
		ChangeDataReturnBean changeDataReturnBean = userService.changeData(transferData);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(changeDataReturnBean);
		return json;
		
	}
	
	
}