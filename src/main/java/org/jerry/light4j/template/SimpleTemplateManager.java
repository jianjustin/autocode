package org.jerry.light4j.template;

import java.io.File;
import java.util.List;

import org.jerry.light4j.utils.FileUtils;
import org.jerry.light4j.utils.FreeMarkerUtils;

import freemarker.template.Template;

/**
 * 模板管理器简单实现（可通过继承实现覆写）
 * @author jian
 */
public class SimpleTemplateManager implements TemplateManager{
	public String templateName = null;//选用模板名称
	public String templatePath = null;//选用模板根路径
	
	public SimpleTemplateManager(String templateName) {
		this.templateName = templateName;
		this.templatePath = BASE_TEMPLATE_PATH + templateName;
	}

	@Override
	public void getTemplates() {
		List<File> files = FileUtils.queryAllFiles(templatePath);
		if(null == files || files.size() == 0) {
			return;
		}
		
		for (File file : files) {
			String templateName = file.getName();
			Template template = FreeMarkerUtils.getTemplate(templateName, file.getPath().replaceAll(templateName, ""));
			paths.add(file.getPath());
			templates.put(file.getPath(),template);
		}
	}

	@Override
	public String getTemplateName() {
		return this.templateName;
	}

	@Override
	public String getTemplatePath() {
		return this.templatePath;
	}

}
