package com.henry.wechat.service.impl;

import com.henry.wechat.bean.WxAccessToken;
import com.henry.wechat.service.WxMpConfigStorage;

/**
 * Created by w-teng on 2016/12/4.
 */
public class WxMpInMemoryConfigStorageImpl implements WxMpConfigStorage {

    private WxAccessToken accessToken;

    public String getToken() {
        return this.accessToken.getAccess_token();
    }

    public void updateAccessToken(WxAccessToken accessToken) {
        this.accessToken = accessToken;
    }

}
