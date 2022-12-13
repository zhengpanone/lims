package com.zp.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.enums.StatusEnum;
import com.zp.sys.controller.dto.RegisterDTO;
import com.zp.sys.controller.dto.UserDTO;
import com.zp.sys.entity.SysUser;
import com.zp.sys.entity.SysUserPost;
import com.zp.sys.entity.SysUserRole;
import com.zp.sys.mapper.SysUserMapper;
import com.zp.sys.mapper.SysUserPostMapper;
import com.zp.sys.mapper.SysUserRoleMapper;
import com.zp.sys.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * RoleServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IUserService {
    //private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserPostMapper sysUserPostMapper;

    /**
     * 保存用户
     *
     * @param userDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(UserDTO userDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDTO, sysUser);
        sysUser.setLockFlag(StatusEnum.ACTIVE.getValue());
//        sysUser.setPassword(userDTO.getPassword());// TODO 密码加密
        sysUserMapper.insert(sysUser);
        userDTO.getRole().stream().map(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRole.setRoleId(roleId);
            return sysUserRole;
        }).forEach(sysUserRoleMapper::insert);
        Optional.ofNullable(userDTO.getPost()).ifPresent(posts -> {
            posts.stream().map(postId -> {
                SysUserPost sysUserPost = new SysUserPost();
                sysUserPost.setPostId(postId);
                sysUserPost.setUserId(sysUser.getUserId());
                return sysUserPost;
            }).forEach(sysUserPostMapper::insert);
        });
        return Boolean.TRUE;
    }

    @Override
    public SysUser register(RegisterDTO registerDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(registerDTO, sysUser);
        // 查询用户是否存在
        Integer count = sysUserMapper.existUserName(sysUser.getUserName());
        if(count!=null){
            return null;
        }
        sysUserMapper.insert(sysUser);
        return sysUser;

    }
}
