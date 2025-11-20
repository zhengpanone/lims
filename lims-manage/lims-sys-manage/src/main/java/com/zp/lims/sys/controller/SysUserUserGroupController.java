package com.zp.lims.sys.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysUserUserGroup;
import com.zp.lims.sys.service.ISysUserUserGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户用户组管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "用户用户组管理")
@RequestMapping("/userUserGroup")
@RequiredArgsConstructor
@Slf4j
public class SysUserUserGroupController extends BaseController {
    
    private final ISysUserUserGroupService sysUserUserGroupService;

    @ApiOperation("根据用户ID查询用户组")
    @GetMapping("/user/{userId}")
    public R<List<SysUserUserGroup>> getByUserId(@ApiParam("用户ID") @PathVariable Long userId) {
        List<SysUserUserGroup> userUserGroups = sysUserUserGroupService.getByUserId(userId);
        return R.success(userUserGroups);
    }

    @ApiOperation("根据用户组ID查询用户")
    @GetMapping("/userGroup/{userGroupId}")
    public R<List<SysUserUserGroup>> getByUserGroupId(@ApiParam("用户组ID") @PathVariable Long userGroupId) {
        List<SysUserUserGroup> userUserGroups = sysUserUserGroupService.getByUserGroupId(userGroupId);
        return R.success(userUserGroups);
    }

    @ApiOperation("为用户分配用户组")
    @PostMapping("/assign/{userId}")
    public R<Boolean> assignUserGroupsToUser(
            @ApiParam("用户ID") @PathVariable Long userId,
            @ApiParam("用户组ID列表") @RequestBody List<Long> userGroupIds) {
        Boolean result = sysUserUserGroupService.assignUserGroupsToUser(userId, userGroupIds);
        return R.success(result);
    }

    @ApiOperation("删除用户用户组关联")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("关联ID") @PathVariable Long id) {
        boolean result = sysUserUserGroupService.removeById(id);
        return R.success(result);
    }
} 