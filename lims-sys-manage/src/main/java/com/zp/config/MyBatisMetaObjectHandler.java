package com.zp.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatisMetaObjectHandler
 *
 * @author zhengpanone
 * @since 2022-11-30
 */
@Slf4j
@Component
public class MyBatisMetaObjectHandler implements MetaObjectHandler {
    private final  String createTime = "createTime";
    private final  String createUser = "createUser";
    private final  String updateTime = "updateTime";
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        if(null==getFieldValByName(createTime,metaObject)){
            this.setFieldValByName(createTime, LocalDateTime.now(), metaObject);
        }if(null==getFieldValByName(createUser,metaObject)){
            // TODO
            this.setFieldValByName(createUser, "", metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        this.setFieldValByName(updateTime, LocalDateTime.now(), metaObject);
    }
}
