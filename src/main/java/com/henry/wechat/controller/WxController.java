package com.henry.wechat.controller;

import com.henry.wechat.service.impl.WxMpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by w-teng on 2016/11/27.
 */
@Controller
@RequestMapping("/")
public class WxController {

    @Autowired
    private WxMpServiceImpl wxMpService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        log.info("======****get root path request");

        return "index";
    }

    @RequestMapping(value="wechat/", method=RequestMethod.GET)
    public void verifyMpServer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("======****get wechat path request to verify mp server");


        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timeStamp = request.getParameter("timestamp");

        if (!this.wxMpService.checkSignature(timeStamp, nonce, signature)) {
            response.getWriter().println("illegal request");
            return;
        }

        String echoStr = request.getParameter("echostr");
        if (echoStr.length()>0) {   //StringUtils.isNotBlank(echoStr) cann't resolve this method
            String echoStrOut = String.copyValueOf(echoStr.toCharArray());
            response.getWriter().println(echoStrOut);
            return;
        }
    }

}
