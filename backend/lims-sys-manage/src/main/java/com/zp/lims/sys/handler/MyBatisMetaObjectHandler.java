package com.zp.lims.sys.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zp.lims.common.core.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * MyBatisMetaObjectHandler
 *
 * @author zhengpanone
 * @since 2022-11-30
 */
@Slf4j
@Component
public class MyBatisMetaObjectHandler implements MetaObjectHandler {
    private final String createTime = "createTime";
    private final String createUser = "createUser";
    private final String updateTime = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        try {
            if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
                if (Objects.isNull(entity.getCreateTime())) {
                    entity.setCreateTime(LocalDateTime.now());
                }
                if (Objects.isNull(entity.getUpdateTime())) {
                    entity.setUpdateTime(LocalDateTime.now());
                }
                strictInsertFill(metaObject, "deleted", Integer.class, 0);

            } else {
                LocalDateTime localDateTime = LocalDateTime.now();
                this.strictInsertFill(metaObject, createTime, LocalDateTime.class, localDateTime);
                this.strictInsertFill(metaObject, updateTime, LocalDateTime.class, localDateTime);
            }
        } catch (Exception e) {
            log.error("自动注入异常：{}", e);
            throw new RuntimeException("自动注入异常：" + e.getMessage());
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        try {
            if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
                entity.setUpdateTime(LocalDateTime.now());

                // 登录用户的信息
                if (Objects.isNull(entity.getCreateUser())) {
                    //SysUserSessionVo userSessionVo = SessionUtil.getUser();
                    //if (Objects.nonNull(userSessionVo)) {
                    //    entity.setUpdateUser(userSessionVo.getId());
                    //} else {
                    //    log.error("自动注入警告，用户未登录");
                    //}
                }
            } else {
                this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }
        } catch (Exception e) {
            log.error("自动注入异常：{}", e);
            throw new RuntimeException("自动注入异常：" + e.getMessage());
        }
    }
}
