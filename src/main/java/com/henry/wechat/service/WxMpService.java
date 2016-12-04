package com.henry.wechat.service;

/**
 * Created by w-teng on 2016/12/4.
 */
public interface WxMpService {
    boolean checkSignature(String timestamp, String nonce, String signature);
    String getAccessToken();


}

