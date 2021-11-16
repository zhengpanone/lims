package com.zp;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * ScrewMain
 *
 * @author zhengpanone
 * @since 2021-10-18
 */
public class ScrewMain {



    private static final String dbURL = "jdbc:oracle:thin:@10.49.62.27:1521:dev";
    private  static final String userName = "GLIMS_SP";
    private static final String password = "Mgidb2021";

    private static final String FILE_OUTPUT_DIR = "d://";

    public static void main(String [] args) throws SQLException {
        EngineConfig engineConfig = EngineConfig.builder()
                .fileOutputDir(FILE_OUTPUT_DIR)
                .openOutputDir(true)
                .fileType(EngineFileType.WORD)
                .produceType(EngineTemplateType.freemarker)
                .build();

        Configuration config = Configuration.builder()
                .version("1.0")
                .description("数据库文档")
                .dataSource(buildDataSource())
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(buildProcessConfig())
                .build();
        new DocumentationExecute(config).execute();

    }

    /**
     * 创建数据源
     *  指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
     * @return
     */
    private static DataSource buildDataSource() throws SQLException {
        /*DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        druidDataSource.setUrl(dbURL);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.init();
        return druidDataSource;*/
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        hikariConfig.setJdbcUrl(dbURL);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);
        // 设置可以获取 tables remarks 信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        // 创建数据源
        return new HikariDataSource(hikariConfig);
    }

    /**
     * 创建screw的处理配置，一般可以忽略
     * @return
     */
    private static ProcessConfig buildProcessConfig(){


        ProcessConfig processConfig = ProcessConfig.builder()
                //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                //根据名称指定表生成
                .designatedTableName(Collections.<String>emptyList())// 根据名称指定表生成
                //根据表前缀生成
                .designatedTablePrefix(Collections.<String>emptyList()) //根据表前缀生成
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>()) // 根据表后缀生成
                //忽略表名
                .ignoreTableName(Arrays.asList("test_user", "test_group"))
                //忽略表前缀
                .ignoreTablePrefix(Collections.singletonList("test_"))
                //忽略表后缀
                .ignoreTableSuffix(Collections.singletonList("_test")).build();
        return  processConfig;
    }
}
