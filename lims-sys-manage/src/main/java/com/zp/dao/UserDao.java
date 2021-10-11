package com.zp.dao;

import com.zp.domain.sys.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/07 20:52.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
public interface UserDao extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {
    SysUser findByMobile(String mobile);
}
