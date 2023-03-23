package com.zp.sys.service;

import com.zp.sys.entity.SysLookupClassifyEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysLookupServiceTest {
    @Autowired
    private ISysLookupClassifyService sysLookupClassifyService;
    @Test
    public void test(){
        SysLookupClassifyEntity sysLookupClassify = SysLookupClassifyEntity.builder().classifyCode("A01").classifyName("测试字典").build();
        Boolean aBoolean = sysLookupClassifyService.addLookupClassify(sysLookupClassify);
        Assertions.assertTrue(aBoolean);
        List<SysLookupClassifyEntity> list = sysLookupClassifyService.list();
        list.forEach(System.out::println);
    }
}
