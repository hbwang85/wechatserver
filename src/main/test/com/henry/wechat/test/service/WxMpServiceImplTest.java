package com.henry.wechat.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.wechat.service.impl.WxMpInMemoryConfigStorageImpl;
import com.henry.wechat.service.impl.WxMpServiceImpl;

import static org.junit.Assert.assertNotNull;

/**
 * Created by w-teng on 2016/12/4.
 */
public class WxMpServiceImplTest {
    private WxMpInMemoryConfigStorageImpl mockconfigStorage;
    private ObjectMapper mockObjectMapper;
    private WxMpServiceImpl mockMpService;


    @org.junit.Before
    public void setUp() throws Exception {
        mockconfigStorage = new WxMpInMemoryConfigStorageImpl();
        mockObjectMapper = new ObjectMapper();
        mockMpService = new WxMpServiceImpl(mockconfigStorage, mockObjectMapper);

    }

    @org.junit.After
    public void tearDown() throws Exception {
        mockObjectMapper = null;
        mockconfigStorage = null;
    }

    @org.junit.Test
    public void getAccessToken() throws Exception {
        String token = mockMpService.getAccessToken();
        assertNotNull(token);
    }

}