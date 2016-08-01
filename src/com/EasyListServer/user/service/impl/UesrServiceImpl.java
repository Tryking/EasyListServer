package com.EasyListServer.user.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.formula.functions.Even;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EasyListServer.user.mapper.EventMapper;
import com.EasyListServer.user.mapper.UserMapper;
import com.EasyListServer.user.pojo.ChangeDataReturnBean;
import com.EasyListServer.user.pojo.DayEvent;
import com.EasyListServer.user.pojo.Event;
import com.EasyListServer.user.pojo.EventExample;
import com.EasyListServer.user.pojo.LoginReturnBean;
import com.EasyListServer.user.pojo.TransferData;
import com.EasyListServer.user.pojo.User;
import com.EasyListServer.user.pojo.UserExample;
import com.EasyListServer.user.pojo.UserExample.Criteria;
import com.EasyListServer.user.service.UserService;
import com.EasyListServer.user.utils.SplitUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
@Service
public class UesrServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EventMapper eventMapper;

	@Override
	public LoginReturnBean loginAndShow(User user) {
		LoginReturnBean loginReturnBean = new LoginReturnBean();
		
		
		if(user.getMemberid() == null){
			loginReturnBean.setResult("0");
			loginReturnBean.setMsg("memberid为空");
			return loginReturnBean;
		}
		
		//判断是否新用户
		List<User> list = selectUser(user.getMemberid());
		
		if(list==null || list.size()==0){
			//新用户插入
			//User newUser = insertUser(user);
			user.setCreatedtime(new Date());
			user.setUpdatedtime(new Date());
			userMapper.insert(user);
			loginReturnBean.setNewAccount(true);
			
			//SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			loginReturnBean.setUser(user);
			
			//新用户是没有数据的
			loginReturnBean.setResult("1");
			loginReturnBean.setMsg("欢迎新用户");
			
			String date = new DateTime().toString("yyyyMMdd");
			List<Event> eventList = getEventByDate(user.getMemberid(), date);
			
			DayEvent dayEvent = new DayEvent();
			dayEvent.setDate(date);
			dayEvent.setMemberId(user.getMemberid());
			dayEvent.setEventList(eventList);
			loginReturnBean.setDayEvent(dayEvent);
			return loginReturnBean;
			
		}
		
		User selectUser = list.get(0);
		
		//老用户
		loginReturnBean.setNewAccount(false);
		loginReturnBean.setUser(selectUser);
		
		String date = new DateTime().toString("yyyyMMdd");
		List<Event> eventList = getEventByDate(selectUser.getMemberid(), date);
		
		DayEvent dayEvent = new DayEvent();
		dayEvent.setDate(date);
		dayEvent.setMemberId(selectUser.getMemberid());
		dayEvent.setEventList(eventList);
		
		loginReturnBean.setDayEvent(dayEvent);
		
		loginReturnBean.setResult("1");
		loginReturnBean.setMsg("欢迎回来");
		//查询一天的事件
		//loginReturnBean.setDayEvent(dayEvent);
		
		return loginReturnBean;
	}
	
	
	@Override
	public ChangeDataReturnBean changeData(TransferData transferData) {

		//数据为空
		if(transferData.getMemberId()==null || transferData.getMemberId() == ""){
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("0", "更新失败：用户为空", false);
			return changeDataReturnBean;
		} else if(transferData.getDate() ==null || transferData.getDate() == ""){
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("0", "更新失败：没有日期", false);
			return changeDataReturnBean;
		} else if (transferData.getStartTimes()=="" && transferData.getEndTimes()=="" && transferData.getEventTypes()=="" && transferData.getSpecificEvents()=="") {
			//除了memberId和date 其余全是""  删除
			EventExample example = new EventExample();
			EventExample.Criteria delCriteria = example.createCriteria();
			delCriteria.andMemberidEqualTo(transferData.getMemberId());
			delCriteria.andDateEqualTo(transferData.getDate());
			eventMapper.deleteByExample(example);
			
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("1", "更新成功", true);
			return changeDataReturnBean;
			
		}else if (transferData.getStartTimes()=="" || transferData.getEndTimes()=="" || transferData.getEventTypes()=="") {
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("0", "更新失败：数据为空", false);
			return changeDataReturnBean;
			
		}
		
		//取出数组
		String[] startTimes = transferData.getStartTimes().split(",");
		String[] endTimes = transferData.getEndTimes().split(",");
		String[] eventTypes = transferData.getEventTypes().split(",");
		String[] specificEvents = new SplitUtil().splitString(transferData.getSpecificEvents());
		
		//数据内容长度不匹配
		if(startTimes.length!=endTimes.length || startTimes.length!= eventTypes.length || startTimes.length!=specificEvents.length
				|| endTimes.length!=eventTypes.length || endTimes.length!=specificEvents.length || eventTypes.length!=specificEvents.length){
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("0", "更新失败：数据长度不匹配", false);
			return changeDataReturnBean;
		}
		
		EventExample example = new EventExample();
		EventExample.Criteria delCriteria = example.createCriteria();
		delCriteria.andMemberidEqualTo(transferData.getMemberId());
		delCriteria.andDateEqualTo(transferData.getDate());
		eventMapper.deleteByExample(example);
		
		//全部插入
		for (int i = 0; i < startTimes.length; i++) {
			
			Event event = new Event();
			event.setMemberid(transferData.getMemberId());
			event.setDate(transferData.getDate());
			event.setStarttime(startTimes[i]);
			event.setEndtime(endTimes[i]);
			event.setEventtypes(Integer.valueOf(eventTypes[i]));
			event.setRecord(specificEvents[i]);
			
			eventMapper.insert(event);
		}
		
		ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("1", "更新成功", true);
		return changeDataReturnBean;
		
	}

	private ChangeDataReturnBean setChangeDataReturnBean(String result,String msg,boolean success){
		ChangeDataReturnBean changeDataReturnBean = new ChangeDataReturnBean();
		changeDataReturnBean.setResult(result);
		changeDataReturnBean.setMsg(msg);
		changeDataReturnBean.setAmendSuccess(success);
		return changeDataReturnBean;
	}
	
	/**
	 * 获取用户每天的事件
	 */
	@Override
	public List<Event> getEventByDate(String memberId, String date) {
		EventExample example = new EventExample();
		com.EasyListServer.user.pojo.EventExample.Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberId);
		criteria.andDateEqualTo(date);
		List<Event> list = eventMapper.selectByExample(example);
		return list;
	}
	
	
	/**
	 * 根据memberId查找用户
	 */
	private List<User> selectUser(String memberId) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberId);
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	/**
	 * 展示所有的event
	 * @param memberId
	 * @param date
	 * @return
	 */
	private LoginReturnBean returnBean(String memberId, String date){
		
		LoginReturnBean loginReturnBean = new LoginReturnBean();
		DayEvent dayEvent = new DayEvent();
		
		User user = selectUser(memberId).get(0);
		
		List<Event> list = getEventByDate(memberId, date);
		
		dayEvent.setDate(date);
		dayEvent.setMemberId(memberId);
		dayEvent.setEventList(list);
		
		loginReturnBean.setNewAccount(false);
		loginReturnBean.setUser(user);
		loginReturnBean.setDayEvent(dayEvent);
		
		return loginReturnBean;
		
	}


}
