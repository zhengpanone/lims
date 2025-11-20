package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysMenu;
import com.zp.lims.sys.mapper.SysMenuMapper;
import com.zp.lims.sys.service.ISysMenuService;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
}
