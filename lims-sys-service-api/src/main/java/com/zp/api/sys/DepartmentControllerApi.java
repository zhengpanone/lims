package com.zp.api.sys;

import com.zp.domain.sys.Department;
import com.zp.response.Result;
import io.swagger.annotations.Api;

/**
 * DepartmentControllerApi
 *
 * @author zhengpanone
 * @since 2021-11-23
 */
@Api(value = "部门管理接口",tags = {"Sys 部门管理接口,提供增删改查"})
public interface DepartmentControllerApi {

    Result save(Department department);
}
