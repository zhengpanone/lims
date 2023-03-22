package com.zp.sys.controller;

import com.zp.response.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MenuController
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @GetMapping("/getMenus")
    public R<?> getMenus() {
        return R.success();
    }
}
