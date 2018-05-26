package org.jerry.light4j.business.domain;

/**
 * 电科表字段映射
 * @author chenjianrui
 *
 */
public class WorkingDomain {

	/**
	 * 字段名称
	 */
	private String fieldsName;
	/**
	 * java变量名称
	 */
	private String className;
	/**
	 * 字段长度
	 */
	private String fieldsNum;
	/**
	 * 字段类型
	 */
	private String fieldsType;
	/**
	 * java变量类型
	 */
	private String classType;
	/**
	 * 字段说明
	 */
	private String fieldsRemark;
	public String getFieldsName() {
		return fieldsName;
	}
	public void setFieldsName(String fieldsName) {
		this.fieldsName = fieldsName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getFieldsNum() {
		return fieldsNum;
	}
	public void setFieldsNum(String fieldsNum) {
		this.fieldsNum = fieldsNum;
	}
	public String getFieldsType() {
		return fieldsType;
	}
	public void setFieldsType(String fieldsType) {
		this.fieldsType = fieldsType;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getFieldsRemark() {
		return fieldsRemark;
	}
	public void setFieldsRemark(String fieldsRemark) {
		this.fieldsRemark = fieldsRemark;
	}
	
	
}
