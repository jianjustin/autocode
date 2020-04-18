package org.jerry.light4j.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jerry.light4j.config.ParsingManager;
import org.jerry.light4j.utils.FileUtils;
import org.jerry.light4j.utils.FreeMarkerUtils;
import org.jerry.light4j.utils.PropertiesUtils;

import freemarker.template.Template;

/**
 * 代码生成器
 * @author chenjianrui
 *
 */
public class GenerateManager {
	
	public final static String BASE_TEMPLATE_PATH = System.getProperty("user.dir") + "src/main/resources/templates/";
	public ParsingManager parsingManager;
	
	public GenerateManager(ParsingManager parsingManager) {
		this.parsingManager = parsingManager;
	}
	
	public String codegenMain() {
		Map<String, Object> dataMap = parsingManager.parsing();
		Properties properties = PropertiesUtils.getProperties("templateConfig.properties");//获取配置信息
		String templatePath = BASE_TEMPLATE_PATH+properties.getProperty("template");
		String codePath = properties.getProperty("codePath");
		String result = "";
		/*1.根据模板路径获取所有模板*/
		List<File> templates = FileUtils.queryAllFiles(templatePath);
		if(null == templates || templates.size() == 0){
			result = "无模板存在,请重新选择目录";
			return result;
		}
		
		for (File file : templates) {
			String templateName = file.getName();//获取单一模板名称
			String templatePath1 = file.getPath().replace(templateName, "");//获取单一模板路径
            String filePath = templatePath1.replace(templatePath, codePath);//获取文件路径
            String fileName = templateName.replace("tableName", dataMap.get("tableName").toString());
            fileName = fileName.replace("serviceName", dataMap.get("serviceName").toString());
            Template template = FreeMarkerUtils.getTemplate(templateName,templatePath1);
    		if(null == template){
    			templateName = templateName.substring(0, templateName.length()-4);
    			template = FreeMarkerUtils.getTemplate(templateName,templatePath1);
    			if(null == template){
    				result += "模板"+templateName+"不存在,请重新选择目录";
        			continue;
    			}
    		}
    		/**3.判断目录是否存在（不存在则进行创建）*/
    		File path = new File(filePath);
    		if(!path.exists()){
    			path.mkdirs();
    		}
    		File file1 = new File(filePath+fileName);
    		FileOutputStream fos;
			try {
				fos = new FileOutputStream(file1);
				Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
				template.process(dataMap,out);
			} catch (Exception e) {
				result = "逆向生成异常";
				return result;
			}
            result += fileName + "文件创建成功";
		}
		return result;
	}
}
