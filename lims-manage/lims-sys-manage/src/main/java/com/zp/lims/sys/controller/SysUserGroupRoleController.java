package com.zp.lims.sys.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysUserGroupRole;
import com.zp.lims.sys.service.ISysUserGroupRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户组角色管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "用户组角色管理")
@RequestMapping("/userGroupRole")
@RequiredArgsConstructor
@Slf4j
public class SysUserGroupRoleController extends BaseController {
    
    private final ISysUserGroupRoleService sysUserGroupRoleService;

    @ApiOperation("根据用户组ID查询角色")
    @GetMapping("/userGroup/{userGroupId}")
    public R<List<SysUserGroupRole>> getByUserGroupId(@ApiParam("用户组ID") @PathVariable Long userGroupId) {
        List<SysUserGroupRole> userGroupRoles = sysUserGroupRoleService.getByUserGroupId(userGroupId);
        return R.success(userGroupRoles);
    }

    @ApiOperation("根据角色ID查询用户组")
    @GetMapping("/role/{roleId}")
    public R<List<SysUserGroupRole>> getByRoleId(@ApiParam("角色ID") @PathVariable Long roleId) {
        List<SysUserGroupRole> userGroupRoles = sysUserGroupRoleService.getByRoleId(roleId);
        return R.success(userGroupRoles);
    }

    @ApiOperation("为用户组分配角色")
    @PostMapping("/assign/{userGroupId}")
    public R<Boolean> assignRolesToUserGroup(
            @ApiParam("用户组ID") @PathVariable Long userGroupId,
            @ApiParam("角色ID列表") @RequestBody List<Long> roleIds) {
        Boolean result = sysUserGroupRoleService.assignRolesToUserGroup(userGroupId, roleIds);
        return R.success(result);
    }

    @ApiOperation("删除用户组角色关联")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("关联ID") @PathVariable Long id) {
        boolean result = sysUserGroupRoleService.removeById(id);
        return R.success(result);
    }
} 