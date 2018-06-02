package org.jerry.light4j.business.domain;

public class ColumnDomain {

	/** 数据库字段名称 **/
    private String columnName;
    /** 数据库字段类型 **/
    private String columnType;
    /** 数据库字段注释 **/
    private String columnRemark;
    /** 对象字段类型**/
    private String modelType;
    /** 对象字段名称**/
    private String modelName;
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
