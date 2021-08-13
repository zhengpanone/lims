package com.zp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 21:07.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@RestController
public class HelloController {
    @RequestMapping("/")
    public String Hello(){
        return "Hello Test";
    }
}
