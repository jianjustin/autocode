package ${package_name}.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ${package_name}.domain.${Prefix}${Suffix};
import ${package_name}.repository.${Prefix}${Suffix}Repository;
import ${package_name}.service.${Prefix}${Suffix}Service;
import ${root_package_name}.common.base.repository.impl.BaseQueryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@RestController
@RequestMapping("/${prefix}/${suffix}")
public class ${Prefix}${Suffix}Cotrollor{
    @Autowired
	private ${Prefix}${Suffix}Service ${prefix}${Suffix}Service;
    @Autowired
    private BaseQueryRepositoryImpl<${Prefix}${Suffix}, Serializable> baseQueryRepositoryImpl;
    @Autowired
	private ${Prefix}${Suffix}Repository ${prefix}${Suffix}Repository;
	
	private ResponseEntity<Map<String,Object>> responseEntity;
    
    @ApiOperation(value="数据插入", notes="创建${prefix}_${suffix}数据",response = ${Prefix}${Suffix}.class, tags = { "${prefix}.${suffix}",})
    @RequestMapping(value="/save", method=RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = {"text/plain", "application/json"})
	public ResponseEntity<?> save(
			@ApiParam(value = "${prefix}_${suffix}数据", required = true) @RequestBody ${Prefix}${Suffix} ${prefix}${Suffix}) {
    	${prefix}${Suffix}Service.save(${prefix}${Suffix});
		return new ResponseEntity<${Prefix}${Suffix}>(HttpStatus.OK);
	}
    
    @ApiOperation(value="数据删除", notes="删除${prefix}_${suffix}数据",response = ${Prefix}${Suffix}.class, tags = { "${prefix}.${suffix}",})
    @RequestMapping(value="/delete/{${prefix}${Suffix}Code}", method=RequestMethod.DELETE, produces = "application/json; charset=UTF-8", consumes = {"text/plain", "application/json"})
	public ResponseEntity<?> delete(
			@ApiParam(value = "${prefix}_${suffix}数据code", required = true) @PathVariable String ${prefix}${Suffix}Code) {
		${Prefix}${Suffix} old${Prefix}${Suffix} = ${prefix}${Suffix}Repository.findBy${Prefix}${Suffix}Code(${prefix}${Suffix}Code);
		${prefix}${Suffix}Service.delete(old${Prefix}${Suffix});
		return new ResponseEntity<${Prefix}${Suffix}>(HttpStatus.OK);
	}
    
    @ApiOperation(value="数据更新", notes="更新${prefix}_${suffix}数据",response = ${Prefix}${Suffix}.class, tags = { "${prefix}.${suffix}",})
    @RequestMapping(value="/update", method=RequestMethod.PUT, produces = "application/json; charset=UTF-8", consumes = {"text/plain", "application/json"})
	public ResponseEntity<?> update(
			@ApiParam(value = "${prefix}_${suffix}数据", required = true) @RequestBody ${Prefix}${Suffix} ${prefix}${Suffix}) {
		${prefix}${Suffix}Service.update(${prefix}${Suffix});
		return new ResponseEntity<${Prefix}${Suffix}>(HttpStatus.OK);
	}
	
    @ApiOperation(value="数据查询", notes="查询${prefix}_${suffix}数据",response = ${Prefix}${Suffix}.class, tags = { "${prefix}.${suffix}",})
    @RequestMapping(value="/queryByCode/{${prefix}${Suffix}Code}", method=RequestMethod.GET, produces = "application/json; charset=UTF-8", consumes = {"text/plain", "application/json"})
    public ResponseEntity<?> queryByCode(
			@ApiParam(value = "${prefix}_${suffix}数据code", required = true) @PathVariable String ${prefix}${Suffix}Code) {
		${prefix}${Suffix}Repository.findBy${Prefix}${Suffix}Code(${prefix}${Suffix}Code);
		return new ResponseEntity<${Prefix}${Suffix}>(HttpStatus.OK);
	}
    
	public ${Prefix}${Suffix}Service get${Prefix}${Suffix}Service() {
		return ${prefix}${Suffix}Service;
	}
	public void set${Prefix}${Suffix}Service(${Prefix}${Suffix}Service ${prefix}${Suffix}Service) {
		this.${prefix}${Suffix}Service = ${prefix}${Suffix}Service;
	}
	public BaseQueryRepositoryImpl<${Prefix}${Suffix}, Serializable> getBaseQueryRepositoryImpl() {
		return baseQueryRepositoryImpl;
	}
	public void setBaseQueryRepositoryImpl(
			BaseQueryRepositoryImpl<${Prefix}${Suffix}, Serializable> baseQueryRepositoryImpl) {
		this.baseQueryRepositoryImpl = baseQueryRepositoryImpl;
	}
	public ${Prefix}${Suffix}Repository get${Prefix}${Suffix}Repository() {
		return ${prefix}${Suffix}Repository;
	}
	public void set${Prefix}${Suffix}Repository(${Prefix}${Suffix}Repository ${prefix}${Suffix}Repository) {
		this.${prefix}${Suffix}Repository = ${prefix}${Suffix}Repository;
	}   
}
