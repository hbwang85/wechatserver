package com.henry.wechat.bean;

import java.io.Serializable;

/**
 * Created by w-teng on 2016/12/4.
 */
public class WxAccessToken implements Serializable{
    private static final long serialVersionUID = -4883574174794487935L;

    private String access_token;
    private int expires_in = -1;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

//    public String getAccessToken() {
//        return access_token;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.access_token = accessToken;
//    }
//
//    public int getExpiresIn() {
//        return expires_in;
//    }
//
//    public void setExpiresIn(int expiresIn) {
//        this.expires_in = expiresIn;
//    }

}
