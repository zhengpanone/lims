package com.zp.sys.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.zp.sys.entity.SysI18nEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
@Slf4j
public class SysI18nMapperTest {
    @Autowired
    SysI18nMapper sysI18nMapper;

    @Test
    public void testInsert() {
        SysI18nEntity sysI18nEntity = SysI18nEntity.builder().key("张三").langType("zh_CN").value("test").build();
        int insert = sysI18nMapper.insert(sysI18nEntity);
        log.info("是否插入成功={}", insert == 1);

    }
}
