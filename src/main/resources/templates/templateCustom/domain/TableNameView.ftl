package ${package_name}.domain;
import java.util.Map;

import ${root_package_name}.common.page.PageQueryBean;
import ${root_package_name}.common.page.PageTools;

/**
* 描述：视图层实体数据封装
* @author tools
*/
public class ${TableName}View extends ${TableName}{
	
	private PageQueryBean pageQueryBean;
	private PageTools pageTools;
	private Map<String,Object> resultMap;
	public PageQueryBean getPageQueryBean() {
		return pageQueryBean;
	}
	public void setPageQueryBean(PageQueryBean pageQueryBean) {
		this.pageQueryBean = pageQueryBean;
	}
	public PageTools getPageTools() {
		return pageTools;
	}
	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	
}