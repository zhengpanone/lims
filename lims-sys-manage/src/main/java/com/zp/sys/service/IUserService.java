package com.zp.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.sys.controller.dto.RegisterDTO;
import com.zp.sys.controller.dto.UserDTO;
import com.zp.sys.entity.SysUser;

/**
 * RoleService
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
public interface IUserService extends IService<SysUser> {
    Boolean saveUser(UserDTO userDTO);

    /**
     * 注册功能
     */
    SysUser register(RegisterDTO registerDTO);
}