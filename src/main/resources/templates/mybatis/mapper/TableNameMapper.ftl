package ${base_package}.mapper;

import org.apache.ibatis.annotations.*;
import ${base_package}.domain.${TableName};
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component(value = "${TableName?uncap_first}Mapper")
public interface ${TableName}Mapper {

    @Select("SELECT * FROM ${table_name}")
    @Results({
    <#if model_column?exists>
        <#list model_column as model>
    	@Result(property = "${model.modelName}",  column = "${model.columnName}"),
        </#list>
    </#if>
    })
    List<${TableName}> findAll();

    @Select("SELECT <#list model_column as model>${model.columnName},</#list> FROM ${table_name} WHERE id = ${r'#{id}'}")
    @Results({
    <#if model_column?exists>
        <#list model_column as model>
    	@Result(property = "${model.modelName}",  column = "${model.columnName}"),
        </#list>
    </#if>
    })
    ${TableName} findOne(int id);

    @Insert("INSERT INTO ${table_name}(<#list model_column as model>${model.columnName},</#list>) VALUES(<#list model_column as model>${"#{"+model.modelName+"}"},</#list>)")
    void insert(${TableName} ${TableName?uncap_first});

    @Update("UPDATE ${table_name} SET <#list model_column as model>${model.columnName}=${"#{"+model.modelName+"}"},</#list>)
    void update(${TableName} ${TableName?uncap_first});

    @Delete("DELETE FROM ${table_name} WHERE id = ${r'#{id}'}")")
    void delete(int id);
}
