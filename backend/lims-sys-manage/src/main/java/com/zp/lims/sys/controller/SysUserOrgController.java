package com.zp.lims.sys.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysUserOrg;
import com.zp.lims.sys.service.ISysUserOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户组织管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "用户组织管理")
@RequestMapping("/userOrg")
@RequiredArgsConstructor
@Slf4j
public class SysUserOrgController extends BaseController {
    
    private final ISysUserOrgService sysUserOrgService;

    @ApiOperation("根据用户ID查询组织")
    @GetMapping("/user/{userId}")
    public R<List<SysUserOrg>> getByUserId(@ApiParam("用户ID") @PathVariable Long userId) {
        List<SysUserOrg> userOrgs = sysUserOrgService.getByUserId(userId);
        return R.success(userOrgs);
    }

    @ApiOperation("根据组织ID查询用户")
    @GetMapping("/org/{orgId}")
    public R<List<SysUserOrg>> getByOrgId(@ApiParam("组织ID") @PathVariable Long orgId) {
        List<SysUserOrg> userOrgs = sysUserOrgService.getByOrgId(orgId);
        return R.success(userOrgs);
    }

    @ApiOperation("为用户分配组织")
    @PostMapping("/assign/{userId}")
    public R<Boolean> assignOrgsToUser(
            @ApiParam("用户ID") @PathVariable Long userId,
            @ApiParam("组织ID列表") @RequestBody List<Long> orgIds) {
        Boolean result = sysUserOrgService.assignOrgsToUser(userId, orgIds);
        return R.success(result);
    }

    @ApiOperation("删除用户组织关联")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("关联ID") @PathVariable Long id) {
        boolean result = sysUserOrgService.removeById(id);
        return R.success(result);
    }
} 