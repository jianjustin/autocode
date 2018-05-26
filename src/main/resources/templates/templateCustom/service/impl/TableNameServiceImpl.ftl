package ${package_name}.service.impl;

import ${root_package_name}.common.base.repository.impl.BaseQueryRepositoryImpl;
import ${package_name}.domain.${TableName};
import ${package_name}.repository.${TableName}Repository;
import ${package_name}.service.${TableName}Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${TableName}ServiceImpl implements ${TableName}Service{
	@Autowired
	private ${TableName}Repository ${tableName}Repository;
	@Autowired
	private BaseQueryRepositoryImpl<${TableName}, Serializable> baseQueryRepositoryImpl;
	
	public ${TableName} save(${TableName} ${tableName}) {
		return ${tableName}Repository.save(${tableName});
	}
	
	public ${TableName} update(${TableName} ${tableName}) {
		return ${tableName}Repository.save(${tableName});
	}

	public ${TableName} delete(${TableName} ${tableName}) {
		${tableName}Repository.delete(${tableName});
	    return ${tableName};
	}
	
	public  List<${TableName}> queryAllByJPQL(String jpql, List<Object> paramValueList) {
		return baseQueryRepositoryImpl.queryAllByJPQL(jpql, paramValueList, ${TableName}.class);
	}

	public List<Map<String, Object>> queryAllColumnByJPQL(String jpql,List<Object> paramValueList) {
		return baseQueryRepositoryImpl.queryAllColumnByJPQL(jpql, paramValueList, ${TableName}.class);
	}

	public  List<${TableName}> queryAllBySQL(String sql, List<Object> paramValueList) {
		return baseQueryRepositoryImpl.queryAllBySQL(sql, paramValueList, ${TableName}.class);
	}

	public List<Map<String, Object>> queryAllColumnBySQL(String sql,List<Object> paramValueList) {
		return baseQueryRepositoryImpl.queryAllColumnBySQL(sql, paramValueList, ${TableName}.class);
	}
	

	public ${TableName}Repository get${TableName}Repository() {
		return ${tableName}Repository;
	}
	public void set${TableName}Repository(${TableName}Repository ${tableName}Repository) {
		this.${tableName}Repository = ${tableName}Repository;
	}
	public BaseQueryRepositoryImpl<${TableName}, Serializable> getBaseQueryRepositoryImpl() {
		return baseQueryRepositoryImpl;
	}
	public void setBaseQueryRepositoryImpl(BaseQueryRepositoryImpl<${TableName}, Serializable> baseQueryRepositoryImpl) {
		this.baseQueryRepositoryImpl = baseQueryRepositoryImpl;
	}
}
