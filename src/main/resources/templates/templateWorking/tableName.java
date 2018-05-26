package com.baosight.pscs.common.ph.domain;

import com.baosight.iplat4j.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.ep.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.util.StringUtils;
/**
 * Tphct10 
 * table comment : 合同过程数据表 
 */
public class Tphct10 extends DaoEPBase {
	
<#list model_column as model>
	private ${model.classType} ${model.className} = "";	/*${model.fieldsRemark}*/
</#list>	
	/**
	 * initialize the metadata 
	 */
	public void initMetaData(){
	EiColumn eiColumn;
<#list model_column as model>
	private ${model.classType} ${model.className};	/*${model.fieldsRemark}*/
	eiColumn = new EiColumn("${model.className}");
	eiColumn.setFieldLength("${model.fieldsNum}");
	eiColumn.setDescName("${model.fieldsRemark}");
	eiMetadata.addMeta(eiColumn);
</#list>	
	}
	/**
	 * the constructor
	 */
	public Tphct10() {
		initMetaData();
	}
	
<#list model_column as model>
	/**
	 * get the ${model.className} - ${model.fieldsRemark}
	 * @return the ${model.className}
	 */
	public Long get${model.className?cap_first}() {
		return this.${model.className};
	}
	
	/**
	 * set the ${model.className} - 合同流水号
	 */
	public void set${model.className?cap_first}(${model.classType} ${model.className}) {
		this.${model.className} = ${model.className};
	}
</#list>	
	
	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {
	<#list model_column as model>
		set${model.className?cap_first}(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("${model.className}")), ${model.className}));
	</#list>	
	}
	
	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
			map.put("bpoId",StringUtils.toString(bpoId, eiMetadata.getMeta("bpoId")));	
	<#list model_column as model>
		map.put("${model.className}",StringUtils.toString(${model.className}, eiMetadata.getMeta("${model.className}")));	
	</#list>	
		return map;
	
	}
}