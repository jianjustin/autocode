package org.jerry.light4j;

import org.jerry.light4j.config.ParsingJdbcManager;
import org.jerry.light4j.generate.GenerateManager;

/**
 * 代码生成应用入口
 * @author jian
 *
 */
public class CodeGenerateApplication {
	
	public static void main(String[] args) throws Exception {
		GenerateManager generateManager = new GenerateManager(new ParsingJdbcManager());
		generateManager.codegenMain();
	}

}
