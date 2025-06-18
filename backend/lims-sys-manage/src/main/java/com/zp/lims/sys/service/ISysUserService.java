package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.controller.dto.LoginDTO;
import com.zp.lims.sys.controller.dto.RegisterDTO;
import com.zp.lims.sys.controller.dto.UserDTO;
import com.zp.lims.sys.controller.vo.LoginResponseVO;
import com.zp.lims.sys.entity.SysUser;

/**
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户服务接口
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser findByUsername(String username);

    boolean existsByUsername(String username);

    boolean updateStatusByUsername(String username, Integer status);


    Boolean saveUser(UserDTO userDTO);


}
