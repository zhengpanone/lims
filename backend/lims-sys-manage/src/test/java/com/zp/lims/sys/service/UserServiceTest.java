package com.zp.lims.sys.service;

import com.zp.lims.sys.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * IUserService
 *
 * @author zhengpanone
 * @since 2022-11-29
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void getUsers() {
        List<SysUser> list = sysUserService.list();
        assertEquals(1, list.size());
        list.forEach(System.out::println);
    }
}
