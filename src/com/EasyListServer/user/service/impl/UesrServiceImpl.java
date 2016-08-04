package com.EasyListServer.user.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.EasyListServer.user.pojo.DayEventReturnBean;
import com.EasyListServer.user.pojo.Event;
import com.EasyListServer.user.pojo.EventExample;
import com.EasyListServer.user.pojo.LoginReturnBean;
import com.EasyListServer.user.pojo.TransferData;
import com.EasyListServer.user.pojo.User;
import com.EasyListServer.user.pojo.UserExample;
import com.EasyListServer.user.pojo.UserExample.Criteria;
import com.EasyListServer.user.pojo.ViewMonthReturnBean;
import com.EasyListServer.user.service.UserService;
import com.EasyListServer.user.utils.SplitUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Service
public class UesrServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EventMapper eventMapper;

	@Override
	public LoginReturnBean loginAndShow(User user) {
		LoginReturnBean loginReturnBean = new LoginReturnBean();

		if (user.getMemberid() == null) {
			loginReturnBean.setResult("0");
			loginReturnBean.setMsg("memberidΪ��");
			return loginReturnBean;
		}

		// �ж��Ƿ����û�
		List<User> list = selectUser(user.getMemberid());

		if (list == null || list.size() == 0) {
			// ���û�����
			// User newUser = insertUser(user);
			user.setCreatedtime(new Date());
			user.setUpdatedtime(new Date());
			userMapper.insert(user);
			loginReturnBean.setNewAccount(true);

			// SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			loginReturnBean.setUser(user);

			// ���û���û�����ݵ�
			loginReturnBean.setResult("1");
			loginReturnBean.setMsg("��ӭ���û�");

			String date = new DateTime().toString("yyyyMMdd");
			List<Event> eventList = getEvent(user.getMemberid(), date);

			DayEvent dayEvent = new DayEvent();
			dayEvent.setDate(date);
			dayEvent.setMemberId(user.getMemberid());
			dayEvent.setEventList(eventList);
			loginReturnBean.setDayEvent(dayEvent);
			return loginReturnBean;

		}

		User selectUser = list.get(0);

		// ���û�
		loginReturnBean.setNewAccount(false);
		loginReturnBean.setUser(selectUser);

		String date = new DateTime().toString("yyyyMMdd");
		List<Event> eventList = getEvent(selectUser.getMemberid(), date);

		DayEvent dayEvent = new DayEvent();
		dayEvent.setDate(date);
		dayEvent.setMemberId(selectUser.getMemberid());
		dayEvent.setEventList(eventList);

		loginReturnBean.setDayEvent(dayEvent);

		loginReturnBean.setResult("1");
		loginReturnBean.setMsg("��ӭ����");
		// ��ѯһ����¼�
		// loginReturnBean.setDayEvent(dayEvent);

		return loginReturnBean;
	}

	@Override
	public ChangeDataReturnBean changeData(TransferData transferData) {

		// ����Ϊ��
		if (transferData.getMemberId() == null || transferData.getMemberId() == "") {
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("0", "����ʧ�ܣ��û�Ϊ��", false);
			return changeDataReturnBean;
		} else if (transferData.getDate() == null || transferData.getDate() == "") {
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("0", "����ʧ�ܣ�û������", false);
			return changeDataReturnBean;
		} else if (transferData.getStartTimes() == "" && transferData.getEndTimes() == ""
				&& transferData.getEventTypes() == "" && transferData.getSpecificEvents() == "") {
			// ����memberId��date ����ȫ��"" ɾ��
			EventExample example = new EventExample();
			EventExample.Criteria delCriteria = example.createCriteria();
			delCriteria.andMemberidEqualTo(transferData.getMemberId());
			delCriteria.andDateEqualTo(transferData.getDate());
			eventMapper.deleteByExample(example);

			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("1", "���³ɹ�", true);
			return changeDataReturnBean;

		} else if (transferData.getStartTimes() == "" || transferData.getEndTimes() == ""
				|| transferData.getEventTypes() == "") {
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("0", "����ʧ�ܣ�����Ϊ��", false);
			return changeDataReturnBean;

		}

		// ȡ������
		String[] startTimes = transferData.getStartTimes().split(",");
		String[] endTimes = transferData.getEndTimes().split(",");
		String[] eventTypes = transferData.getEventTypes().split(",");
		String[] specificEvents = new SplitUtil().splitString(transferData.getSpecificEvents());

		// �������ݳ��Ȳ�ƥ��
		if (startTimes.length != endTimes.length || startTimes.length != eventTypes.length
				|| startTimes.length != specificEvents.length || endTimes.length != eventTypes.length
				|| endTimes.length != specificEvents.length || eventTypes.length != specificEvents.length) {
			ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("0", "����ʧ�ܣ����ݳ��Ȳ�ƥ��", false);
			return changeDataReturnBean;
		}

		EventExample example = new EventExample();
		EventExample.Criteria delCriteria = example.createCriteria();
		delCriteria.andMemberidEqualTo(transferData.getMemberId());
		delCriteria.andDateEqualTo(transferData.getDate());
		eventMapper.deleteByExample(example);

		// ȫ������
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

		ChangeDataReturnBean changeDataReturnBean = setChangeDataReturnBean("1", "���³ɹ�", true);
		return changeDataReturnBean;

	}

	private ChangeDataReturnBean setChangeDataReturnBean(String result, String msg, boolean success) {
		ChangeDataReturnBean changeDataReturnBean = new ChangeDataReturnBean();
		changeDataReturnBean.setResult(result);
		changeDataReturnBean.setMsg(msg);
		changeDataReturnBean.setAmendSuccess(success);
		return changeDataReturnBean;
	}

	/**
	 * ��ȡ�û�ÿ����¼�
	 */
	@Override
	public DayEventReturnBean getEventByDate(String memberId, String date) {
		DayEventReturnBean dayEvent = new DayEventReturnBean();
		if (memberId == null || date == null) {
			dayEvent.setResult("0");
			dayEvent.setMsg("����Ϊ��");
			return dayEvent;
		}
		EventExample example = new EventExample();
		com.EasyListServer.user.pojo.EventExample.Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberId);
		criteria.andDateEqualTo(date);
		List<Event> list = eventMapper.selectByExample(example);
		if (list.isEmpty()) {
			dayEvent.setResult("0");
			dayEvent.setMsg("����û���������");
			return dayEvent;
		}
		dayEvent.setResult("1");
		dayEvent.setDate(date);
		dayEvent.setMemberId(memberId);
		dayEvent.setEventList(list);
		dayEvent.setMsg("��ȡ�������ݳɹ�");
		return dayEvent;
	}

	private List<Event> getEvent(String memberId, String date) {
		EventExample example = new EventExample();
		com.EasyListServer.user.pojo.EventExample.Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberId);
		criteria.andDateEqualTo(date);
		List<Event> list = eventMapper.selectByExample(example);
		return list;
	}

	/**
	 * ����memberId�����û�
	 */
	private List<User> selectUser(String memberId) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberId);
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	/**
	 * չʾ���е�event
	 * 
	 * @param memberId
	 * @param date
	 * @return
	 */
	private LoginReturnBean returnBean(String memberId, String date) {

		LoginReturnBean loginReturnBean = new LoginReturnBean();
		DayEvent dayEvent = new DayEvent();

		User user = selectUser(memberId).get(0);

		List<Event> list = getEvent(memberId, date);

		dayEvent.setDate(date);
		dayEvent.setMemberId(memberId);
		dayEvent.setEventList(list);

		loginReturnBean.setNewAccount(false);
		loginReturnBean.setUser(user);
		loginReturnBean.setDayEvent(dayEvent);

		return loginReturnBean;

	}

	@Override
	public ViewMonthReturnBean viewMonthData(String memberId, String month) {
		ViewMonthReturnBean viewMonthReturnBean = new ViewMonthReturnBean();

		if (memberId == null || month == null) {
			viewMonthReturnBean.setResult("0");
			viewMonthReturnBean.setMsg("����Ϊ��");
			return viewMonthReturnBean;
		}

		viewMonthReturnBean.setMemberId(memberId);
		viewMonthReturnBean.setMonth(month);

		EventExample example = new EventExample();
		EventExample.Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberId);
		criteria.andDateLike(month + "%");
		example.setOrderByClause("DATE DESC");
		List<Event> allMonthList = eventMapper.selectByExample(example);

		List<DayEvent> monthList = new ArrayList<>();

		if (allMonthList.size() != 0) {
			String date = allMonthList.get(0).getDate();
			List<Event> dayList = new ArrayList<>();

			for (Event event : allMonthList) {

				if (event.getDate().equals(date)) {

					dayList.add(event);

				} else {

					DayEvent dayEvent = new DayEvent();
					dayEvent.setDate(date);
					dayEvent.setMemberId(memberId);

					ArrayList<Event> arrayList = new ArrayList<>();
					arrayList.addAll(dayList);

					dayEvent.setEventList(arrayList);

					monthList.add(dayEvent);

					date = event.getDate();

					dayList.clear();

					dayList.add(event);

				}

				if (event == allMonthList.get(allMonthList.size() - 1)) {
					DayEvent dayEvent = new DayEvent();
					dayEvent.setDate(date);
					dayEvent.setMemberId(memberId);
					dayEvent.setEventList(dayList);

					monthList.add(dayEvent);

				}

			}

			viewMonthReturnBean.setResult("1");
			viewMonthReturnBean.setMsg("��ѯ�ɹ�");
			viewMonthReturnBean.setMonthEvents(monthList);
		} else {
			viewMonthReturnBean.setResult("1");
			viewMonthReturnBean.setMsg("��Ӧ�·�û������");
			return viewMonthReturnBean;
		}

		return viewMonthReturnBean;
	}
}
