package com.zp.lims.sys.controller;

import com.zp.lims.sys.service.ISysRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RoleController
 *
 * @author zhengpanone
 * @since 2022-11-30
 */
@RestController
@RequestMapping("sys/role")
public class SysRoleController {
    /**
     * 构造方法注入
     */
    ISysRoleService roleService;

    public SysRoleController(ISysRoleService roleService) {
        this.roleService = roleService;
    }

    /*@GetMapping("/page")
    public R<PageResult<RoleVO>> getAdmins(RoleDTO roleDTO) {
        Page<RoleVO> page = new Page<>(roleDTO.getPage(), roleDTO.getLimit());
        LambdaQueryWrapper<RoleVO> wrapper = new LambdaQueryWrapper<RoleVO>().
                eq(StringUtils.isNotEmpty(roleDTO.getName()), RoleVO::getRoleName, "%" + roleDTO.getName() + "%");
        IPage page1 = roleService.page(page, wrapper);

        PageResult<RoleVO> responsePage = new PageResult<>();
        //responsePage.setList(page1.getRecords());
        return R.success();
    }*/
}
