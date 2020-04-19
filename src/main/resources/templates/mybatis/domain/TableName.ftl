package ${base_package}.domain;
import java.util.Date;

/**
* 描述：${table_name}模型
* @author tools
*/
public class ${TableName}{
	
    <#if model_column?exists>
        <#list model_column as model>
    /**
    *${model.columnRemark!}
    */
    private ${model.modelType!} ${model.modelName?uncap_first};
        </#list>
    </#if>

}