package org.jerry.light4j.config;

import java.util.Map;

/**
 * 解析模块（通过连接文档或者数据库，获取参数集合）
 * @author jian
 */
public interface ParsingManager {
	public final static String TABLE_STRING = "TABLE_STRING";
	public final static String basePath = System.getProperty("user.dir");//项目根目录
	
	public Map<String, Object> parsing();

}
