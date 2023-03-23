package com.zp.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zp.base.controller.BaseController;

@RestController
public class TestController extends BaseController {
    @GetMapping("/")
    public String test() {
        return "lims sample manage";
    }
}
