package org.jerry.light4j.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 字段结构说明
 * @author jian
 */
public class ColumnDomain {
	
	
	
	/** 数据库字段名称 **/
    public String columnName;
    /** 数据库字段类型 **/
    public String columnType;
    /** 数据库字段说明 **/
    public String columnRemark;
    /** 对象字段类型**/
    public String modelType;
    /** 对象字段名称**/
    public String modelName;
    
    public static Map<String, String> mysqlTypeToJavaTypeMap = new HashMap<>();
	
	static {
		mysqlTypeToJavaTypeMap.put("BOOL", "Boolean");
		mysqlTypeToJavaTypeMap.put("BOOLEAN", "Boolean");
		mysqlTypeToJavaTypeMap.put("SMALLINT", "Integer");
		mysqlTypeToJavaTypeMap.put("MEDIUMINT", "Integer");
		mysqlTypeToJavaTypeMap.put("INT", "Integer");
		mysqlTypeToJavaTypeMap.put("INTEGER", "Integer");
		mysqlTypeToJavaTypeMap.put("BIGINT", "Long");
		mysqlTypeToJavaTypeMap.put("FLOAT", "Float");
		mysqlTypeToJavaTypeMap.put("DOUBLE", "Double");
		mysqlTypeToJavaTypeMap.put("DECIMAL", "BigDecimal");
		mysqlTypeToJavaTypeMap.put("DATE", "Date");
		mysqlTypeToJavaTypeMap.put("DATETIME", "Date");
		mysqlTypeToJavaTypeMap.put("TIMESTAMP", "Timestamp");
		mysqlTypeToJavaTypeMap.put("TIME", "Time");
		mysqlTypeToJavaTypeMap.put("YEAR", "Date");
		mysqlTypeToJavaTypeMap.put("CHAR", "String");
		mysqlTypeToJavaTypeMap.put("VARCHAR", "String");
		mysqlTypeToJavaTypeMap.put("VARCHAR2", "String");
		mysqlTypeToJavaTypeMap.put("TINYTEXT", "String");
		mysqlTypeToJavaTypeMap.put("TEXT", "String");
		mysqlTypeToJavaTypeMap.put("MEDIUMTEXT", "String");
		mysqlTypeToJavaTypeMap.put("LONGTEXT", "String");
		mysqlTypeToJavaTypeMap.put("ENUM", "String");
		mysqlTypeToJavaTypeMap.put("SET", "String");
		mysqlTypeToJavaTypeMap.put("BINARY", "byte[]");
		mysqlTypeToJavaTypeMap.put("VARBINARY", "byte[]");
		mysqlTypeToJavaTypeMap.put("TINYBLOB", "byte[]");
		mysqlTypeToJavaTypeMap.put("BLOB", "byte[]");
		mysqlTypeToJavaTypeMap.put("MEDIUMBLOB", "byte[]");
		mysqlTypeToJavaTypeMap.put("LONGBLOB", "byte[]");
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnRemark() {
		return columnRemark;
	}

	public void setColumnRemark(String columnRemark) {
		this.columnRemark = columnRemark;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	
}
