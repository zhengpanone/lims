package com.zp.dao;

import com.zp.domain.sys.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/07 20:52.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
public interface UserDao extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {
    /**
     * 根据手机号查找
     * @param mobile
     * @return
     */
    Optional<SysUser> findByMobile(String mobile);

}
