package com.zp.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.zp.exception.BusinessException;
import com.zp.sys.entity.SysLookupClassifyEntity;
import com.zp.sys.mapper.SysLookupClassifyMapper;
import com.zp.sys.service.ISysLookupClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLookupClassifyServiceImpl extends ServiceImpl<SysLookupClassifyMapper, SysLookupClassifyEntity> implements ISysLookupClassifyService {

    @Autowired
    SysLookupClassifyMapper sysLookupClassifyMapper;

    @Override
    public Boolean addLookupClassify(SysLookupClassifyEntity classify) {
        LambdaQueryWrapper<SysLookupClassifyEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysLookupClassifyEntity::getClassifyCode, classify.getClassifyCode());
        if (sysLookupClassifyMapper.selectCount(wrapper) > 0) {
            throw new BusinessException("类别编码已存在");
        }
        wrapper.clear();
        wrapper.eq(SysLookupClassifyEntity::getClassifyName, classify.getClassifyName());
        if (sysLookupClassifyMapper.selectCount(wrapper) > 0) {
            throw new BusinessException("类别名称已存在");
        }
        Boolean result = SqlHelper.retBool(sysLookupClassifyMapper.insert(classify));

        return result;
    }
}
