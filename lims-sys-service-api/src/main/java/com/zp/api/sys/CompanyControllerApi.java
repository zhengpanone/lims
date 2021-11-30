package com.zp.api.sys;

import com.zp.domain.sys.Company;
import com.zp.domain.sys.Department;
import com.zp.response.Result;
import io.swagger.annotations.*;

/**
 * CompanyControllerApi
 *
 * @author zhengpanone
 * @since 2021-09-22
 */
@Api(value = "公司管理接口",tags = {"Sys 公司管理接口,提供增删改查"})
public interface CompanyControllerApi {
    @ApiOperation(value = "新增公司", notes = "新增部门")
    Result save(Company company);


    @ApiOperation(value = "查找所有公司", notes = "所有公司")
    Result<Company> findAll();

    @ApiOperation(value = "根据ID查找公司", notes = "查找公司")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "企业ID", required = true,paramType = "path", dataType = "string")
    )
    Result findById(String id);

    @ApiOperation(value = "根据ID更新公司", notes = "更新公司")
    @ApiImplicitParams(@ApiImplicitParam(name = "id",value = "企业ID"))
    Result update(String id, Company company);

    @ApiOperation(value = "根据ID删除公司", notes = "删除公司")
    @ApiImplicitParams(@ApiImplicitParam(name = "id",value = "企业ID"))
    Result delete(String id);
}
