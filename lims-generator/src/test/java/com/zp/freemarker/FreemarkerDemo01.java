package com.zp.freemarker;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * FreemarkerDemo01
 *
 * @author zhengpanone
 * @since 2022-02-07
 */
public class FreemarkerDemo01 {
    @Test
    public void test01() throws IOException, TemplateException {
        // 1. 创建Freemarker配置对象
        Configuration cfg = new Configuration(Configuration.getVersion());
        // 2. 指定模板加载器,将模板存入缓存中
        // 文件路径模板加载器
        FileTemplateLoader ftl = new FileTemplateLoader(new File("templates"));
        cfg.setTemplateLoader(ftl);
        // 3. 获取模板
        Template template = cfg.getTemplate("template01.ftl");
        // 4. 构造数据模型
        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("username","张三");
        // 5. 文件输出
        // writer(FileWriter 文件输出, PrintWriter 控制台输出)

        template.process(dataModel,new FileWriter("./a.txt"));
        template.process(dataModel,new PrintWriter(System.out));


    }
}
