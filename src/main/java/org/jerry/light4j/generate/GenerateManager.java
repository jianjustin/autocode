package org.jerry.light4j.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.jerry.light4j.config.ParsingManager;
import org.jerry.light4j.template.SimpleTemplateManager;
import org.jerry.light4j.template.TemplateManager;
import org.jerry.light4j.utils.FileUtils;
import org.jerry.light4j.utils.FreeMarkerUtils;
import org.jerry.light4j.utils.PropertiesUtils;
import org.jerry.light4j.utils.StringUtils;

import freemarker.template.Template;

/**
 * 代码生成器
 * @author jian
 */
public class GenerateManager {
	public static Logger logger = Logger.getLogger(GenerateManager.class.getName());
	public final static String BASE_CODE_PATH = System.getProperty("user.dir") + "/src/main/java/";
	public static Properties properties;
	
	public ParsingManager parsingManager;//解析器
	public TemplateManager templateManager;//模板管理器
	
	public String codePath;
	public Map<String, Object> dataMap;
	
	static {
		properties = PropertiesUtils.getProperties("application.properties");
	}
	
	public GenerateManager(ParsingManager parsingManager) throws Exception {
		this(parsingManager, new SimpleTemplateManager(properties.getProperty("template_name")));
	}
	
	public GenerateManager(ParsingManager parsingManager, TemplateManager templateManager) throws Exception{
		this.parsingManager = parsingManager;
		this.templateManager =templateManager;
		
		codePath = BASE_CODE_PATH+properties.getProperty("base_package").replace('.', '/');//获取目标代码位置
		dataMap = parsingManager.parsing();
		templateManager.getTemplates();//获取模板列表，避免重复加载
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void codegenMain() {
		logger.info("==============开始代码生成 Start=================="); 
		
		List<String> tables = (List<String>) dataMap.get(ParsingManager.TABLE_STRING);
		
		for (int i = 0; i < tables.size(); i++) {
			Map<String,Object> map = generateDataMap(tables.get(i));//设置模板参数
			map.put("model_column", dataMap.get(tables.get(i)));
			for (String path : templateManager.paths) {
				Template template = templateManager.templates.get(path);
				
				String templateName = template.getName();
				String fileName = templateName.replaceAll("TableName", map.get("TableName").toString()).replaceAll("ftl", "java");
				String filePath = path.replaceAll(templateName, "").replaceAll(templateManager.getTemplatePath(), codePath);
				
				
	    		try {
	    			File path1 = new File(filePath);
		    		if(!path1.exists())path1.mkdirs();
		    		
		    		File file1 = new File(filePath+fileName);
		    		if(!file1.exists())file1.createNewFile();
		    		
					OutputStream fos = new FileOutputStream(file1);
					Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
					template.process(map,out);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("==============开始代码生成 End=================="); 
	}
	
	/**
	 * 生成参数表
	 * @param table_name
	 * @return
	 */
	public Map<String, Object> generateDataMap(String table_name){
		Map<String,Object> map = new HashMap<>();//设置模板参数
		String modelName = StringUtils.upperFirst(StringUtils.toModelName("_", table_name));
		map.put("table_name", table_name);
		map.put("TableName", modelName);
		map.put("base_package", properties.getProperty("base_package"));
		
		/*根据表名获取系统模块列表*/
		String[] strs = table_name.split("_");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < strs.length; i++) list.add(strs[i].toLowerCase());
		map.put("module_list", list);
		return map;
	}
}
