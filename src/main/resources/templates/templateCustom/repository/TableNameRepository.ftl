package ${package_name}.repository;

import java.io.Serializable;

import ${root_package_name}.common.base.repository.BaseMotifyRepository;
import ${package_name}.domain.${TableName};
import org.springframework.stereotype.Repository;

/********************************************
 * 实体操作接口
 * @author janine
 * @since 2017-12-12
 *********************************************/
@Repository
public interface ${TableName}Repository extends BaseMotifyRepository<${TableName}, Serializable>{

	public ${TableName} findBy${TableName}Code(String ${tableName}Code);

	
}
