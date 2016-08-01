package com.EasyListServer.user.utils;

public class SplitUtil {
	
	public String[] splitString(String string){
		
		//String string = "#^@#^@11#^@111#^@#^@#^@";
		boolean flag = string.endsWith("#^@");
		String[] stringArray = null;
		if(flag == true){
			
			string = string + "1";
			stringArray = string.split("#\\^@");
			stringArray[stringArray.length-1] = "";
			
		} else {
			
			stringArray = string.split("#\\^@");
			
		}
		return stringArray;
		
	}
}
