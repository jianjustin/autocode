package org.jerry.light4j.business.configuration;

import java.util.List;
import java.util.Map;

/**
 * 配置管理器API
 * @author chenjianrui
 *
 */
public interface ConfigurateManagerApi {
	
	public Map<String,Object> getDataMap();
	
	public <T> List<T> getDataList();
	
	public Map<String,Object> start();

}
