package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.controller.dto.RoleDTO;
import com.zp.lims.sys.controller.dto.UserDTO;
import com.zp.lims.sys.entity.SysRole;

/**
 * RoleService
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
public interface ISysRoleService extends IService<SysRole> {

    Boolean saveRole(RoleDTO roleDTO);

    boolean updateStatusById(String username, Integer status);
}
