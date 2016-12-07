package com.henry.wechat.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.wechat.bean.WxAccessToken;
import com.henry.wechat.service.WxMpConfigStorage;
import com.henry.wechat.service.WxMpService;
import com.henry.wechat.util.SHA1;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by w-teng on 2016/12/4.
 */

@Service
public class WxMpServiceImpl implements WxMpService{

    private final WxMpConfigStorage configStorage;
    private final ObjectMapper objectMapper;

    private String mpAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
    private String appID = "wx4b2c2d7ec6621f1f";
    private String appSecret = "21488c46de475cce717b11c1a3a3a4f7";
    private String defaultToken = "hsbc";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WxMpServiceImpl(WxMpConfigStorage configStorage) {
        this.configStorage = configStorage;
        this.objectMapper = new ObjectMapper();

    }

    private String getMpAccessTokenUrl() {
        this.log.info("mp accesstoken url: " + mpAccessTokenUrl + appID + "&secret=" + appSecret);
        return mpAccessTokenUrl + appID + "&secret=" + appSecret;
    }

    public boolean checkSignature(String timestamp, String nonce, String signature) {
        this.log.info("==========****** signature: " + signature + "timestamp: " + timestamp + "nonce: " + nonce);
        return SHA1.gen(defaultToken, timestamp, nonce).equals(signature);
    }

    public String getAccessToken() {

        HttpGet httpGet = new HttpGet(getMpAccessTokenUrl());
        try {
            CloseableHttpResponse response = httpClient().execute(httpGet);
            String resultContent = new BasicResponseHandler().handleResponse(response);
            WxAccessToken at = this.objectMapper.readValue(resultContent, WxAccessToken.class);
            this.configStorage.updateAccessToken(at);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }

        this.log.info("access token: " + this.configStorage.getToken());
        return this.configStorage.getToken();
    }

    private CloseableHttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }

}
