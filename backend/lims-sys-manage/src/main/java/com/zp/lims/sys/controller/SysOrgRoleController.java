package com.zp.lims.sys.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysOrgRole;
import com.zp.lims.sys.service.ISysOrgRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织角色管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "组织角色管理")
@RequestMapping("/orgRole")
@RequiredArgsConstructor
@Slf4j
public class SysOrgRoleController extends BaseController {
    
    private final ISysOrgRoleService sysOrgRoleService;

    @ApiOperation("根据组织ID查询角色")
    @GetMapping("/org/{orgId}")
    public R<List<SysOrgRole>> getByOrgId(@ApiParam("组织ID") @PathVariable Long orgId) {
        List<SysOrgRole> orgRoles = sysOrgRoleService.getByOrgId(orgId);
        return R.success(orgRoles);
    }

    @ApiOperation("根据角色ID查询组织")
    @GetMapping("/role/{roleId}")
    public R<List<SysOrgRole>> getByRoleId(@ApiParam("角色ID") @PathVariable Long roleId) {
        List<SysOrgRole> orgRoles = sysOrgRoleService.getByRoleId(roleId);
        return R.success(orgRoles);
    }

    @ApiOperation("为组织分配角色")
    @PostMapping("/assign/{orgId}")
    public R<Boolean> assignRolesToOrg(
            @ApiParam("组织ID") @PathVariable Long orgId,
            @ApiParam("角色ID列表") @RequestBody List<Long> roleIds) {
        Boolean result = sysOrgRoleService.assignRolesToOrg(orgId, roleIds);
        return R.success(result);
    }

    @ApiOperation("删除组织角色关联")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("关联ID") @PathVariable Long id) {
        boolean result = sysOrgRoleService.removeById(id);
        return R.success(result);
    }
} 