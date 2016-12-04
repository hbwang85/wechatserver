package com.henry.wechat.controller;

import com.henry.wechat.service.WxMpService;
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
    private WxMpService wxMpService;

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value="wechat/", method=RequestMethod.GET)
    public void verifyMpServer(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
