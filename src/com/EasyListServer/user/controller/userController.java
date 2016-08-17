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

import com.EasyListServer.user.pojo.AppVersionReturnBean;
import com.EasyListServer.user.pojo.BaseBean;
import com.EasyListServer.user.pojo.ChangeDataReturnBean;
import com.EasyListServer.user.pojo.DayEventReturnBean;
import com.EasyListServer.user.pojo.Event;
import com.EasyListServer.user.pojo.LoginReturnBean;
import com.EasyListServer.user.pojo.ShareUrlRetrunBean;
import com.EasyListServer.user.pojo.TransferData;
import com.EasyListServer.user.pojo.User;
import com.EasyListServer.user.pojo.ViewMonthReturnBean;
import com.EasyListServer.user.service.MessageService;
import com.EasyListServer.user.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class userController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;

	/**
	 * 登陆之后并且显示全部event，只能显示今天的event
	 * 
	 * @param user
	 *            登录时的user
	 * @return loginAndShow的json
	 */
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String loginAndShow(User user) {
		// try {
		// user.setMemberid(new
		// String(user.getMemberid().getBytes("iso8859-1"),"utf-8"));
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		LoginReturnBean loginAndShow = userService.loginAndShow(user);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(loginAndShow);
		return json;
	}

	/**
	 * 修改之后返回的数据
	 * 
	 * @param transferData
	 * @return
	 */
	@RequestMapping(value = "/changeData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String changeData(TransferData transferData) {
		ChangeDataReturnBean changeDataReturnBean = userService.changeData(transferData);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(changeDataReturnBean);
		return json;

	}

	/**
	 * 
	 * @param memberId
	 * @param month
	 * @return
	 */
	@RequestMapping(value = "/viewMonthData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String viewMonthData(String memberId, String month) {

		ViewMonthReturnBean viewMonthData = userService.viewMonthData(memberId, month);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(viewMonthData);
		return json;
	}

	@RequestMapping(value = "/viewDayData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String viewDayData(String memberId, String date) {
		DayEventReturnBean dayEventReturnBean = userService.getEventByDate(memberId, date);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(dayEventReturnBean);
		return json;
	}

	/**
	 * 
	 * @param memberId
	 * @param month
	 * @return
	 */
	@RequestMapping(value = "/addOneWord", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOneWord(String memberId, String date, String dataType, String oneWord) {
		ChangeDataReturnBean changeDataReturnBean = userService.addOneWord(memberId, date, dataType, oneWord);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(changeDataReturnBean);
		return json;
	}
	
	/**
	 * 查询版本信息
	 * @param deviceType
	 * @param appName
	 * @return
	 */
	@RequestMapping(value = "/appVersion", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String appVersion(String deviceType, String appName){
		System.out.println("deviceType:"+deviceType);
		System.out.println("appName:"+appName);
//		deviceType = "android";
//		appName = "EasyList";
		AppVersionReturnBean appVersionReturnBean = messageService.appVersion(deviceType, appName);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(appVersionReturnBean);
		return json;
	}
	
	/**
	 * 修改个性签名
	 * @param memberId
	 * @param signature
	 * @return
	 */
	@RequestMapping(value = "/updateParam", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateParam(String memberId, String type, String param){
		
//		memberId = "1111";
//		type = "5";
//		param = "";
		
		System.out.println("memberId:"+memberId);
		System.out.println("type:"+type);
		System.out.println("param:"+param);
		
		BaseBean baseBean = messageService.updateParam(memberId, type, param);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(baseBean);
		return json;
	}
	
	/**
	 * 分享链接
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/getShareUrl", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getShareUrl(String memberId){
		
//		memberId = "1";
		
		System.out.println("memberId:"+memberId);
		
		ShareUrlRetrunBean shareUrlRetrunBean = messageService.getShareUrl(memberId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(shareUrlRetrunBean);
		return json;
	}

}
