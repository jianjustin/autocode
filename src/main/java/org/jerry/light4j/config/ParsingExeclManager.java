package org.jerry.light4j.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jerry.light4j.ColumnDomain;
import org.jerry.light4j.utils.ExeclUtils;
import org.jerry.light4j.utils.StringUtils;

/**
 * 针对Execl的解析器
 * @author jian
 *
 */
public class ParsingExeclManager implements ParsingManager{
	
	public Workbook workbook;

	public Map<String, Object> parsing() {
		Map<String, Object> resultMap = new HashMap<>();
		List<Sheet> sheetList = ExeclUtils.getSheetList(workbook);//获取所有工作区，工作区与表对应
		
		if(null == sheetList || sheetList.size() == 0)return resultMap;
		
		for (Sheet sheet : sheetList) {
			String tableName = sheet.getSheetName();//获取表名
			List<Row> rowList = ExeclUtils.getRowList(sheet).stream()
					.filter((item) -> null!=item && item.getPhysicalNumberOfCells()>0)
					.collect(Collectors.toList());//去除空行
			List<ColumnDomain> domains = getColumnsList(rowList);
			resultMap.put(tableName, domains);
		}
		
		return resultMap;
	}
	
	public List<ColumnDomain> getColumnsList(List<Row> rowList){
		List<ColumnDomain> list = new ArrayList<>();
		if(null == rowList || rowList.size() == 0)return list;
		
		for (int i = 1; i < rowList.size(); i++) {
			ColumnDomain item = new ColumnDomain();
			short j = rowList.get(i).getFirstCellNum();
			item.columnName = rowList.get(i).getCell(j++).getStringCellValue();//首列是字段名
			item.columnType = rowList.get(i).getCell(j++).getStringCellValue();//第二列是字段类型
			item.columnRemark = rowList.get(i).getCell(j++).getStringCellValue();//第三列是字段说明
			item.modelName = StringUtils.toModelName("_", item.columnName);
			item.modelType = ColumnDomain.mysqlTypeToJavaTypeMap.get(StringUtils.toSQLTypeName(item.columnType));
			list.add(item);
		}
		
		return list;
	}
	
	/**
	 * 获取execl文件
	 * @param execlPath
	 */
	public void getWorkbook(String execlPath) {
		workbook = ExeclUtils.getWorkbook(basePath+execlPath);
	}

}
