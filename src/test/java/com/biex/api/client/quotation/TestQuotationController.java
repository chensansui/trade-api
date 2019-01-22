package com.biex.api.client.quotation;


import com.biex.api.client.constant.BiexConst;
import com.biex.api.client.tool.HttpUtils;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Quotation test
 */
public class TestQuotationController {

    private String root = BiexConst.ROOT;

    /**
     * Test to get public quotes
     */
    @Test
    public void testTicker() {//ok
        Map<String, String> data = Maps.newHashMap();
        data.put("symbol", "BTC2USDT_LM");
        String json = HttpUtils.get(root + "ticker", data);
        System.out.println(json);
    }

    /**
     * Test K-line market
     */
    @Test
    public void testKline() {//
        Map<String, String> data = Maps.newHashMap();
        data.put("symbol", "ETH2USDT_LSWAP");
        data.put("type", "hour");
        String json = HttpUtils.get(root + "kline", data);
        System.out.println(json);
    }

    /**
     * Test depth market
     */
    @Test
    public void testDepth() {
        Map<String, String> data = Maps.newHashMap();
        data.put("symbol", "BTC2USD_ISWAP");
        String json = HttpUtils.get(root + "depth", data);
        System.out.println(json);
    }

    /**
     * Test transaction record
     */
    @Test
    public void testTrades() {
        Map<String, String> data = Maps.newHashMap();
        data.put("symbol", "ETH2USDT_LSWAP");
        String json = HttpUtils.get(root + "trade", data);
        System.out.println(json);
    }

}
