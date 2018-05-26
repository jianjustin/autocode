package org.jerry.light4j.business.generate;

import java.util.Map;

/**
 * 代码生成器管理者
 * @author chenjianrui
 *
 */
public interface GenerateManagerApi {

	public String codegenMain(Map<String,Object> configurateMap);
}
