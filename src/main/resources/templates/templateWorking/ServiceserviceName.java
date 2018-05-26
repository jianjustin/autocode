package com.baosight.pscs.ph.ct.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baosight.erp.common.util.UserUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.ep.ServiceEPBase;
import com.baosight.iplat4j.util.DateUtils;

import com.baosight.pscs.common.ph.domain.${tableName};

import com.baosight.pscs.ph.ct.common.BpoTool;

/**
 * <p>
 * Title: ${tableRemark}
 * </p>
 * <p>
 * Company: 上海宝信软件
 * </p>
 * 
 * @author 
 * @version 1.1
 */
public class Service${serviceName} extends ServiceEPBase {

	private static Logger logger = Logger.getLogger(Service${serviceName}.class);

	/**
	 * 初始化页面信息
	 * 
	 * @param inInfo
	 * 
	 * @return outInfo
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		inInfo= super.initLoad(inInfo);
		return query(inInfo);

	}

	public EiInfo query(EiInfo inInfo){
		EiInfo outInfo = new EiInfo();
		outInfo = super.query(inInfo, true);
		
		return outInfo;
	}
	
	public EiInfo insert(EiInfo inInfo){
		try{
			for (int i = 0; i <inInfo.getBlock("result").getRowCount() ; i++) {
				${serviceName?cap_first} ${serviceName} = new ${serviceName?cap_first}();
				${serviceName}.fromMap(inInfo.getBlock("result").getRow(i));
				if(${serviceName}.getSeriesNumber()==null ||  "".equals(${serviceName}.getSeriesNumber().trim()) || "null".equals(${serviceName}.getSeriesNumber().trim()) ){
					${serviceName}.setRecCreator(UserSession.getLoginName());
					${serviceName}.setRecCreatorTime(DateUtils.curDateStr8());
					${serviceName}.setRecReviserTime(DateUtils.curDateStr8());
					${serviceName}.setRecReviser(UserSession.getLoginName());
					dao.insert("${serviceName}.insert",${serviceName});
				} 
			}
		}catch(Exception e){
			e.printStackTrace();
			inInfo.setStatus(-1);
		}
		return inInfo;
	}
	public EiInfo update(EiInfo inInfo){
		int conn=0;
		try{
			for (int i = 0; i <inInfo.getBlock("result").getRowCount() ; i++) {
				${serviceName?cap_first} ${serviceName} = new ${serviceName?cap_first}();
				${serviceName}.fromMap(inInfo.getBlock("result").getRow(i)); 
				${serviceName}.setRecReviserTime(DateUtils.curDateStr8());
				${serviceName}.setRecReviser(UserSession.getLoginName());
				dao.update("${serviceName}.update",${serviceName});
				
			}
		}catch(Exception e){
			e.printStackTrace();
			inInfo.setStatus(-1);
		}
		return query(inInfo);
	}
	public EiInfo delete(EiInfo inInfo){
		int conn=0;
		try{
			for (int i = 0; i <inInfo.getBlock("result").getRowCount() ; i++) {
				${serviceName?cap_first} ${serviceName} = new ${serviceName?cap_first}();
				${serviceName}.fromMap(inInfo.getBlock("result").getRow(i));  
				dao.delete("${serviceName}.delete",${serviceName});
				
			}
		}catch(Exception e){
			e.printStackTrace();
			inInfo.setStatus(-1);
		}
		return query(inInfo);
	}
}