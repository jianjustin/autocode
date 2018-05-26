package org.jerry.light4j.common.utils;

import java.lang.reflect.Field;

public class ClassUtils {
	
	public static <T> void setFieldValue(T clazzObj,String fieldName,String fieldValue){
		try {
			Class<? extends Object> clazz = clazzObj.getClass();
			for (Field field : clazz.getDeclaredFields()) {
	            if (field.getName().equals(fieldName)) {
	                field = clazz.getDeclaredField(fieldName);
	                field.setAccessible(true);
	                field.set(clazzObj, fieldValue);// 动态设置值
	                break;
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
