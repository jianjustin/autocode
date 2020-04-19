package ${base_package}.service;

import ${base_package}.domain.${TableName};
import ${base_package}.mapper.${TableName}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${TableName}Service {
    @Autowired
    public ${TableName}Mapper ${TableName?uncap_first}Mapper;

    public List<${TableName}> findAll(){
        return ${TableName?uncap_first}Mapper.findAll();
    }

    public ${TableName} findOne(int id){
        return ${TableName?uncap_first}Mapper.findOne(id);
    }

    public void insert(${TableName} ${TableName?uncap_first}){
        ${TableName?uncap_first}Mapper.insert(${TableName?uncap_first});
    }

    public void update(${TableName} ${TableName?uncap_first}){
        ${TableName?uncap_first}Mapper.update(${TableName?uncap_first});
    }

    public void delete(int id){
        ${TableName?uncap_first}Mapper.delete(id);
    }
}
