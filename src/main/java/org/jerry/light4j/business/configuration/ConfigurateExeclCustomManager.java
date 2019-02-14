package org.jerry.light4j.business.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jerry.light4j.business.domain.ColumnDomain;
import org.jerry.light4j.business.generate.GenerateCustomManager;
import org.jerry.light4j.business.generate.GenerateManagerApi;
import org.jerry.light4j.common.utils.ClassUtils;
import org.jerry.light4j.common.utils.ExeclUtils;
import org.jerry.light4j.common.utils.PropertiesUtils;
import org.jerry.light4j.common.utils.StringUtils;

/**
 * 通过execl获取参数集合
 * @author admin
 *
 */
public class ConfigurateExeclCustomManager implements ConfigurateManagerApi {

	/**
	 * 获取参数列表
	 */
	public Map<String, Object> getDataMap() {
		Properties properties = PropertiesUtils.getProperties("templateCustomConfig.properties");
		Map<String,Object> propertiesMap = PropertiesUtils.getPropertiesMap(properties);
		propertiesMap.put("model_column", getDataList());
		return propertiesMap;
	}

	/**
	 * 获取表字段映射集合
	 */
	@SuppressWarnings("unchecked")
	public List<ColumnDomain> getDataList() {
		List<ColumnDomain> list = new ArrayList<ColumnDomain>();
		Properties properties = PropertiesUtils.getProperties("templateConfig.properties");
		String execlFilePath = properties.get("execlFilePath").toString();
		Workbook workbook = ExeclUtils.getWorkbook(execlFilePath);
		List<Sheet> sheetList = ExeclUtils.getSheetList(workbook);
		List<Row> rowList = ExeclUtils.getRowList(sheetList.get(0));//默认使用首个工作区
		list = getColumnsList(rowList);
		return list;
	}
	
	/**
	 * 通过反射的方式获取字段数据
	 * @param rowList
	 * @return
	 */
	public List<ColumnDomain> getColumnsList(List<Row> rowList){
		List<ColumnDomain> columnDomainList = new ArrayList<ColumnDomain>();
		if(null == rowList || rowList.size() == 0)return columnDomainList;
		Properties properties = PropertiesUtils.getProperties("templateConfig.properties");
		Properties columnsTypeProperties = PropertiesUtils.getProperties("columnsTypeMapping.properties");
		Map<String,String> columnMap = new HashMap<String,String>();
		for (int i = 0; i < rowList.size(); i++) {
			Row row = rowList.get(i);
			if(0 == i){//说明是参照行
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					String cellValue = ExeclUtils.getCellValue(row.getCell(j));
					if(StringUtils.isBlank(cellValue))continue;
					columnMap.put((new Integer(j)).toString(), cellValue);
				}
				continue;
			}
			Set<String> keySet = columnMap.keySet();
			ColumnDomain columnDomain = new ColumnDomain();
			for (String key : keySet) {
				String type = properties.getProperty(columnMap.get(key));
				String value = ExeclUtils.getCellValue(row.getCell(new Integer(key)));
				ClassUtils.setFieldValue(columnDomain, type, value);
			}
			/*设置默认值*/
			if(columnDomain.getColumnType().split("VARCHAR2").length > 1)
				columnDomain.setColumnType("VARCHAR2");
			columnDomain.setModelName(StringUtils.toModelName("_", columnDomain.getColumnName()));
			columnDomain.setModelType(columnsTypeProperties.getProperty(columnDomain.getColumnType()));
			columnDomainList.add(columnDomain);
			
		}
		return columnDomainList;
	}

	/**
	 * 启动器
	 */
	public Map<String,Object> start() {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map = getDataMap();
		} catch (Exception e) {
			return map;
		}
		return map;
	}
	
	public static void main(String[] args) {
		ConfigurateManagerApi configurateManager = new ConfigurateExeclCustomManager();
		Map<String,Object> dataMap = configurateManager.start();
		GenerateManagerApi generateExeclManager = new GenerateCustomManager();
		generateExeclManager.codegenMain(dataMap);
	}

}
