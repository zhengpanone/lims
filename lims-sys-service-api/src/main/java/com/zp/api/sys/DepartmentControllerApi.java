package com.zp.api.sys;

import com.zp.domain.sys.Department;
import com.zp.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * DepartmentControllerApi
 *
 * @author zhengpanone
 * @since 2021-11-23
 */
@Api(value = "部门管理接口", tags = {"Sys 部门管理接口,提供增删改查"})
public interface DepartmentControllerApi {
    @ApiOperation(value = "新增部门", notes = "新增部门")
    Result save(Department department);
    @ApiOperation(value = "查找所有部门", notes = "所有部门")
    Result findAll();
    @ApiOperation(value = "根据ID查找部门", notes = "查找部门")
    Result findById(String id);
    @ApiOperation(value = "根据ID更新部门", notes = "更新部门")
    Result update(String id, Department department);
    @ApiOperation(value = "根据ID删除部门", notes = "删除部门")
    Result delete(String id);
}
