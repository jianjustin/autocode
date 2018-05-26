package org.jerry.light4j.common.utils;

public class StringUtils {

	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 */
	public static Boolean isBlank(Object obj){
		if(obj == null)return true;
		if(obj == "")return true;
		return false;
	}
	
	/**
	 * 将表字段名称转换为属性名称
	 * @param regex
	 * @param oldString
	 * @return
	 */
	public static String toModelName(String regex,String oldString){
		if(isBlank(oldString))
			return null;
		String[] oldStringArr = oldString.split(regex);
		String newString = "";
		for (int i=0 ; i<oldStringArr.length ; i++) {
			if(isBlank(oldStringArr[i]))
				continue;
			if(i == 0)
				newString = oldStringArr[i].toLowerCase();
			else
				newString +=  oldStringArr[i].toLowerCase().substring(0, 1).toUpperCase() + oldStringArr[i].toLowerCase().substring(1, oldStringArr[i].length());
		}
		return newString;
	}
}
