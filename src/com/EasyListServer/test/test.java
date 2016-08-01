package com.EasyListServer.test;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

import com.EasyListServer.user.pojo.User;
import com.google.gson.Gson;

public class test {

	@Test
	public void timeTest(){
		Date date = new Date();
		System.out.println(date);
		String string = date.toString();
		System.out.println(string);
		String string2 = new DateTime().toString("yyyy-MM-dd");
		System.out.println(string2);
	}
	
	@Test
	public void testGson(){
		User user = new User();
		user.setAccount("Ð¡ÁúÅ®");
		user.setMemberid("1234");
		Gson gson = new Gson();
		System.out.println(gson.toJson(user));
	}
	
	public String[] testSplit(String string){
		
		//String string = "#^@#^@11#^@111#^@#^@#^@";
		boolean flag = string.endsWith("#^@");
		String[] stringArray = null;
		if(flag == true){
			
			string = string + "1";
			stringArray = string.split("#\\^@");
			stringArray[stringArray.length-1] = "";
			
		}
		else {
			
			stringArray = string.split("#\\^@");
		}
		return stringArray;
		
	}
	
	@Test
	public void testSplit1(){
		String[] testSplit = testSplit("#^@#^@11#^@111#^@#^@#^@");
		for (int i = 0; i < testSplit.length; i++) {
			System.out.println("testSplit["+i+"]:"+testSplit[i]);
		}
	}
	
	@Test
	public void test(){
		String string ="";
		String[] split = string.split(",");
		System.out.println(split.length);
		for (int i = 0; i < split.length; i++) {
			System.err.println("split["+i+"]:"+split[i]);
		}
	}
	
	
//			string = string+"";
//			String[] stringArray = string.split("#\\^@");
//			for (String string2 : stringArray) {
//				System.out.println(string2);
//			}
//			System.out.println(stringArray.length);
		}
//			String[] stringArray = string.split("#\\^@");
//			stringArray[stringArray.length] = "";
//			for (String string2 : stringArray) {
//				System.out.println(string2);
//			}
//		}
//		String s = "123";
//		char[] charArray = s.toCharArray();
//		for (char c : charArray) {
//			System.out.println(c);
//		}
	
	
