package com.biex.api.client.trade.common.controller;

import com.biex.api.client.constant.BiexConst;
import com.biex.api.client.tool.HttpUtils;
import org.junit.Test;

/**
 * @version : 1.0
 * @discription : Public controller test class
 * @create : 2018-07-07-14
 **/
public class TestCommonController {

    private String root = BiexConst.ROOT;

    /**
     * Test acquisition system time
     */
    @Test
    public void testGetTimestamp() {
        String json = HttpUtils.get(root + "common/timestamp");
        System.out.println(json);
    }

    /**
     * Test to get all currencies
     */
    @Test
    public void testGetCurrencys() {
        String json = HttpUtils.get(root + "common/currencys");
        System.out.println(json);
    }

    /**
     * Test to get all transaction pairs
     */
    @Test
    public void testGetSymbols() {
        String json = HttpUtils.get(root + "common/symbols");
        System.out.println(json);
    }

}
