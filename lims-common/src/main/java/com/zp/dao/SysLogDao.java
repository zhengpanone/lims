package com.zp.dao;

import com.zp.module.SysLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * SysLogDao
 *
 * @author zhengpanone
 * @since 2021-11-17
 */
public interface SysLogDao extends JpaRepository<SysLogEntity,String>, JpaSpecificationExecutor<SysLogEntity> {

}
