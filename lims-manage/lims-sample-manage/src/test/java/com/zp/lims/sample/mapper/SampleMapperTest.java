package com.zp.lims.sample.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.zp.lims.sample.entity.SampleEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
@Slf4j
public class SampleMapperTest {
    @Autowired
    SampleMapper sampleMapper;

    @Test
    public void testFindList() {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setSampleId(1L);
        sampleEntity.setSampleCode("test sample code");
        int insert = sampleMapper.insert(sampleEntity);
        log.info("插入成功{}", insert == 1);
        List<SampleEntity> sampleEntities = sampleMapper.fineSampleList();
        log.info(sampleEntities.toString());
    }
}
