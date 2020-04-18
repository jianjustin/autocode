package org.jerry.light4j.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jerry.light4j.ColumnDomain;
import org.jerry.light4j.utils.PropertiesUtils;
import org.jerry.light4j.utils.StringUtils;


/**
 * 基于数据源的解析器
 * @author jian
 *
 */
public class ParsingJdbcManager implements ParsingManager{
	
	public Connection connection;
	public boolean isAllTables = true;//是否生成所有表
	public List<String> tables = new ArrayList<>();
	
	public ParsingJdbcManager() {
		try {
			Properties properties = PropertiesUtils.getProperties("application.properties");
			Class.forName(properties.getProperty("datasource.driver"));
			connection = DriverManager.getConnection(properties.getProperty("datasource.url"), properties.getProperty("datasource.userName"), properties.getProperty("datasource.password"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showTables(){
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SHOW TABLES");
			while (rs.next()) 
				tables.add(rs.getString(1));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public Map<String, Object> parsing() {
		Map<String, Object> resultMap = new HashMap<>();
		if(isAllTables)showTables();
		if (null == tables || tables.size() == 0) return resultMap;
			
		try {
		for (int i = 0; i < tables.size(); i++) {
			ResultSet rsColimns = connection.getMetaData().getColumns(null, "%", tables.get(i), "%");
			List<ColumnDomain> list = new ArrayList<>();
			while(rsColimns.next()) {
				ColumnDomain item = new ColumnDomain();
				item.columnName = rsColimns.getString("COLUMN_NAME");
				item.columnType = rsColimns.getString("TYPE_NAME");
				item.columnRemark = rsColimns.getString("REMARKS");
				item.modelName = StringUtils.toModelName("_", item.columnName);
				item.modelType = ColumnDomain.mysqlTypeToJavaTypeMap.get(StringUtils.toSQLTypeName(item.columnType));
				list.add(item);
			}
			resultMap.put(tables.get(i), list);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
