package com.zp.api.sys;

import com.zp.domain.sys.Company;
import com.zp.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyControllerApi
 *
 * @author zhengpanone
 * @since 2021-09-22
 */
@Api(value = "公司管理接口",tags = {"Sys 公司管理接口,提供增删改查"})
public interface CompanyControllerApi {
    @ApiOperation("新增公司")
    ResponseResult save(Company company);
}
