package ${package_name}.service;

import java.util.List;
import java.util.Map;

import ${package_name}.domain.${TableName};

/******************************************
 * 实体服务接口
 * @author janine
 *
 *******************************************/
public interface ${TableName}Service {
     /**
      * 实体保存
      * @param ${tableName}
      * @return
      */
     public ${TableName} save(${TableName} ${tableName});
     /**
      * 实体更新
      * @param ${tableName}
      * @return
      */
     public ${TableName} update(${TableName} ${tableName});
     /**
      * 实体删除
      * @param ${tableName}
      * @return
      */
     public ${TableName} delete(${TableName} ${tableName});
     /**
 	 * 根据JPQL语句返回查询实体列表
 	 * @param jpql
 	 * @param paramValueList 参数列表,防止sql注入
 	 * @return
 	 */
 	public  List<${TableName}> queryAllByJPQL(String jpql,List<Object> paramValueList);
 	
 	/**
 	 * 根据JPQL语句返回查询键值对列表
 	 * @param jpql
 	 * @param paramValueList
 	 * @return
 	 */
 	public List<Map<String,Object>> queryAllColumnByJPQL(String jpql,List<Object> paramValueList);
 	
 	/**
 	 * 根据sql语句查询实体列表
 	 * @param sql
 	 * @param paramValueList
 	 * @return
 	 */
 	public  List<${TableName}> queryAllBySQL(String sql,List<Object> paramValueList);
 	
 	/**
 	 * 根据sql语句查询键值对列表
 	 * @param sql
 	 * @param paramValueList
 	 * @return
 	 */
 	public List<Map<String,Object>> queryAllColumnBySQL(String sql,List<Object> paramValueList);
}
