package com.henry.wechat.service;

import com.henry.wechat.bean.WxAccessToken;

/**
 * Created by w-teng on 2016/12/4.
 */
public interface WxMpConfigStorage {
    String getToken();
    void updateAccessToken(WxAccessToken accessToken);

}
