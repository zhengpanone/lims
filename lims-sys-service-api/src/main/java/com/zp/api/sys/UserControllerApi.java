package com.zp.api.sys;

import com.zp.domain.sys.SysUser;
import com.zp.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理接口
 *
 * @author zheng
 */
@Api(value = "系统用户管理接口", tags = {"Sys 用户管理接口,提供增删改查"})
public interface UserControllerApi {
    /**
     * 新增用户
     *
     * @param sysUser 用户对象
     * @return
     */
    @ApiOperation(value = "新增用户",notes = "新增用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user", value = "用户对象", dataTypeClass = SysUser.class, required = true))
    ResponseResult save(SysUser sysUser);

    @ApiOperation("根据ID查找用户")
    ResponseResult findUserById(String id);
}
