package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.zp.lims.sys.entity.SysUser;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * UserTest
 * @SpringBootTest注解里面的classes可以定义只想加载的bean类，
 * 比如此处只需要测试mybatis-plus的mapper接口，那么需要用到的类配置进来即可，
 * 这样就不会加载controller层、service层等没用到的资源。
 * @author zhengpanone
 * @since 2022-11-28
 */
@SpringBootTest(
        classes = {
                SysUserMapper.class,
                DataSourceAutoConfiguration.class,
                MybatisPlusAutoConfiguration.class,
                DataSource.class,
                SqlSessionFactory.class
        }
)
@MapperScan(basePackages = "com.zp.sys.mapper")
public class UserMapperTest {
    @Autowired
    private SysUserMapper userMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapperTest.class);


    @Test
    public void testSelect() {
        List<SysUser> users = userMapper.selectList(null);
        assertEquals(1, users.size());
        users.forEach(System.out::println);
    }
}
