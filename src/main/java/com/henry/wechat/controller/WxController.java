package com.henry.wechat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.wechat.service.impl.WxMpInMemoryConfigStorageImpl;
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
public class WxController {

    @Autowired
    private WxMpServiceImpl wxMpService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    GET /wechatserver-1.0-SNAPSHOT/wechat?signature=57b8b3d75fa526519ffcf32531ebc736b7c6c971&echostr=5437234002469240765&timestamp=1481115964&nonce=1492784901

    public void WxController() {
        WxMpInMemoryConfigStorageImpl configStorage = new WxMpInMemoryConfigStorageImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        this.wxMpService = new WxMpServiceImpl(configStorage);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value="/wechat", method=RequestMethod.GET)
    public void verifyMpServer(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timeStamp = request.getParameter("timestamp");

        log.info("======****get wechat path request to verify mp server\n signature: " + signature +"\n nonce: " + nonce + "\n timestamp: " + timeStamp);


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
