package org.jerry.light4j.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 参数获取工具集
 * @author chenjianrui
 *
 */
public class PropertiesUtils {

	/**
	 * 获取参数列表
	 * @param propertiesName
	 * @return
	 */
	public static Properties getProperties(String propertiesName){
		Properties properties = new Properties();
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(PropertiesUtils.class.getClassLoader().getResourceAsStream(propertiesName),"UTF-8");
			properties.load(inputStreamReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 将参数对象转换成map形式
	 * @param properties
	 * @return
	 */
	public static Map<String,Object> getPropertiesMap(Properties properties){
		Set<Object> keys = properties.keySet();
		Map<String,Object> propertiesMap = new HashMap<String,Object>();
		for (Object key : keys) {
			propertiesMap.put(key.toString(), properties.get(key));
		}
		return propertiesMap;
	}
	
	public static void main(String[] args) {
		Properties properties = getProperties("columnsTypeMapping.properties");
		System.out.println(properties);
	}
}
