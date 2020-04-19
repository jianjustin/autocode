package org.jerry.light4j.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Template;

/**
 * 模板管理器
 * @author jian
 */
public interface TemplateManager {
	/*模板主目录*/
	public final static String BASE_TEMPLATE_PATH = System.getProperty("user.dir") + "/src/main/resources/templates/";
	/*模板列表*/
	public Map<String,Template> templates = new HashMap<>();
	/*模板目录列表*/
	public List<String> paths = new ArrayList<>();
	
	public void getTemplates() throws Exception;
	public String getTemplateName();
	public String getTemplatePath();

}
