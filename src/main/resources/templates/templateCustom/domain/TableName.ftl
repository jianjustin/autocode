package ${package_name}.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* 描述：${table_name}模型
* @author tools
*/
@Entity
@Table(name="${table_name}")
public class ${TableName}{
	
    <#if model_column?exists>
        <#list model_column as model>
    /**
    *${model.columnRemark!}
    */
    @Column(name = "${model.columnName}",columnDefinition = "${model.columnType}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private ${model.modelType!} ${model.modelName?uncap_first};
        </#list>
    </#if>

<#if model_column?exists>
<#list model_column as model>
<#if (model.columnType = 'varchar' || model.columnType = 'text')>
    public ${model.modelType} get${model.modelName}() {
        return this.${model.modelName?uncap_first};
    }
    public void set${model.modelName}(${model.modelType} ${model.modelName?uncap_first}) {
        this.${model.modelName?uncap_first} = ${model.modelName?uncap_first};
    }
	
</#if>
</#list>
</#if>
}