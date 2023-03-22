package com.zp.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.sample.entity.SampleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper  extends BaseMapper<SampleEntity> {
    List<SampleEntity> fineSampleList();
}
