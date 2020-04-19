package org.jian.shadow.controller;

import org.jian.shadow.domain.${TableName};
import org.jian.shadow.service.${TableName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ${TableName}Controller {
    @Autowired
    public ${TableName}Service ${TableName?uncap_first}Service;

    @GetMapping("<#list module_list as model>${"/"+model}</#list>")
    @PreAuthorize("hasAuthority('<#list module_list as model>${model+"."}</#list>findAll')")
    public List<${TableName}> findAll(){
        return ${TableName?uncap_first}Service.findAll();
    }

    @GetMapping("<#list module_list as model>${"/"+model}</#list>/{id}")
    @PreAuthorize("hasAuthority('<#list module_list as model>${model+"."}</#list>findOne')")
    public ${TableName} findOne(@PathVariable("id") int id){
        return ${TableName?uncap_first}Service.findOne(id);
    }

    @PostMapping("<#list module_list as model>${"/"+model}</#list>")
    @PreAuthorize("hasAuthority('<#list module_list as model>${model+"."}</#list>insert')")
    public void insert(@RequestBody ${TableName} ${TableName?uncap_first}){
        ${TableName?uncap_first}Service.insert(${TableName?uncap_first});
    }

    @PutMapping("<#list module_list as model>${"/"+model}</#list>")
    @PreAuthorize("hasAuthority('<#list module_list as model>${model+"."}</#list>update')")
    public void update(@RequestBody ${TableName} ${TableName?uncap_first}){
        ${TableName?uncap_first}Service.update(${TableName?uncap_first});
    }

    @DeleteMapping("<#list module_list as model>${"/"+model}</#list>/{id}")
    @PreAuthorize("hasAuthority('<#list module_list as model>${model+"."}</#list>delete')")
    public void delete(@PathVariable("id") int id){
        ${TableName?uncap_first}Service.delete(id);
    }
}
