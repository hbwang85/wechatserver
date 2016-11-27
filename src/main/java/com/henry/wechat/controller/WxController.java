package com.henry.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by w-teng on 2016/11/27.
 */
@Controller
@RequestMapping({"/", "wechatserver"})
public class WxController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "index";
    }
}
