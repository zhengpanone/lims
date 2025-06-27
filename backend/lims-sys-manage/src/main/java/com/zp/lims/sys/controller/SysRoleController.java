package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.response.PageResult;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.controller.dto.RoleDTO;
import com.zp.lims.sys.controller.dto.RolePageDTO;
import com.zp.lims.sys.controller.dto.UserUpdateDTO;
import com.zp.lims.sys.controller.vo.RoleVO;
import com.zp.lims.sys.entity.SysRole;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * RoleController
 *
 * @author zhengpanone
 * @since 2022-11-30
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class SysRoleController {
    /**
     * 构造方法注入
     */
   private final ISysRoleService roleService;


    @GetMapping("/page")
    public R<PageResult<RoleVO>> getAdmins(RolePageDTO rolePageDTO) {
        Page<SysRole> page = new Page<>(rolePageDTO.getPage(), rolePageDTO.getLimit());
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<SysRole>().
                like(StringUtils.isNotEmpty(rolePageDTO.getName()), SysRole::getName, rolePageDTO.getName());
        IPage result = roleService.page(page, wrapper);
        return R.success(PageResult.pageResult(result));
    }

    @ApiOperation("新增角色")
    @PostMapping("/add")
    public R<?> save(@Validated @RequestBody RoleDTO roleDTO) {
        try {
            Boolean result = roleService.saveRole(roleDTO);
            return R.success(result);
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return R.error(e.getMessage());
        }
    }

    @ApiOperation("更新角色")
    @PostMapping("/update")
    public R<Boolean> update(@Validated @RequestBody SysRole sysRole) {
        boolean result = roleService.updateById(sysRole);
        return R.success(result);
    }

    @ApiOperation("更新角色状态")
    @PostMapping("/updateState")
    public R<Boolean> updateRoleState(@Validated @RequestBody UserUpdateDTO updateDTO) {
        String id = updateDTO.getId();
        Integer status = updateDTO.getStatus();
        boolean result = roleService.updateStatusById(id,status);
        return R.success(result);
    }


    @ApiOperation("根据ID查询角色")
    @GetMapping("/{id}")
    public R<SysRole> getById(@ApiParam("角色ID") @PathVariable String id) {
        SysRole role = roleService.getById(id);
        return R.success(role);
    }
}
