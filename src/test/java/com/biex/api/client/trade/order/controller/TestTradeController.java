package com.biex.api.client.trade.order.controller;

import com.biex.api.client.BiexServiceFactory;
import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.service.OrderService;
import com.biex.api.client.service.bean.*;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @version : 1.0
 * @discription : Test transaction controller
 * @create : 2018-07-07-16
 **/
public class TestTradeController {

    //Use timestamp as AES key
    BiexServiceFactory factory = BiexServiceFactory.newInstance();

    OrderService orderService = factory.newOrderService();

    /**
     * change account leverage
     *
     * @throws Exception
     */
    @Test
    public void changeLeverage() {//ok
        ChangeLeverageBean data = new ChangeLeverageBean();
        data.setSymbol("BTC2USDT_LM");
        data.setLever(5l);
        ApiResponse response = orderService.changeLeverage(data);
        System.out.println(response.getBody());
    }

    /**
     * Single order
     *
     * @throws Exception
     */
    @Test
    public void doMatchRequest() {//ok
        EntrustBean data = new EntrustBean();
        data.setAmount(new BigDecimal("1"));
        data.setSide("sell");
        data.setPrice(new BigDecimal(3550));
//        data.setSymbol("BTC2USD_IM");

        data.setSymbol("BTC2USDT_LM");
        data.setType("limitPrice");
        ApiResponse response = orderService.doMatchRequest(data);
        System.out.println(response.getBody());
    }

    /**
     * Transaction cancellation (single stroke)
     *
     * @throws Exception
     */
    @Test
    public void doMatchCancelRequest() {
        EntrustCancelBean data = new EntrustCancelBean();
        data.setOrderId(181949768967270402l);
        data.setSymbol("BTC2USDT_LM");
        ApiResponse response = orderService.doMatchCancelRequest(data);
        System.out.println(response.getBody());
    }

    /**
     * Transaction cancellation (multiple)
     *
     * @throws Exception
     */
    @Test
    public void doMatchCancelBatchRequest() {//ok
        OrderCancelbatchBean data = new OrderCancelbatchBean();
        List<Long> req = Lists.newArrayList();
        req.add(180237855489142784l);
//        req.add(180240935601451008l);
        data.setOrderIds(req);
        data.setSymbol("BTC2USD_ISWAP");

        ApiResponse response = orderService.doMatchCancelBatchRequest(data);
        System.out.println(response.getBody());
    }

    /**
     * Transaction cancellation (all)
     *
     * @throws Exception
     */
    @Test
    public void doMatchCancelAllRequest() {//ok
        OrderCancelbatchBean data = new OrderCancelbatchBean();
        data.setSymbol("BTC2USDT_LM");

        ApiResponse response = orderService.doMatchCancelAllRequest(data);
        System.out.println(response.getBody());
    }

    /**
     * Query order details
     *
     * @throws Exception
     */
    @Test
    public void searchOrderInfo() {//OK
        OrderInfoBean data = new OrderInfoBean();
        data.setOrderId(181949768967270402l);
        data.setSymbol("BTC2USDT_LM");
        ApiResponse response = orderService.searchOrderInfo(data);
        System.out.println(response.getBody());
    }

    /**
     * Query order transaction details
     *
     * @throws Exception
     */
    @Test
    public void orderDealInfo() {//OK
        OrderInfoBean data = new OrderInfoBean();
        data.setOrderId(181949768967270402l);
        data.setSymbol("BTC2USDT_LM");
        ApiResponse response = orderService.orderDealInfo(data);
        System.out.println(response.getBody());
    }

    /**
     * Current delegation list
     *
     * @throws Exception
     */
    @Test
    public void orderCurrent() {//ok
        OrderCurrentBean data = new OrderCurrentBean();
        data.setSide("sell");
        data.setSymbol("BTC2USD_ISWAP");
        data.setType("limitPrice");
//        data.setPage(1);
//        data.setRows(10);
        ApiResponse response = orderService.orderCurrent(data);
        System.out.println(response.getBody());
    }

    /**
     * Historical delegation list
     *
     * @throws Exception
     */
    @Test
    public void orderHistory() {//ok
        OrderCurrentBean data = new OrderCurrentBean();
        data.setPage(1);
        data.setRows(10);
//        data.setDirect("buy");
        data.setSymbol("BTC2USDT_LM");
        data.setType("limitPrice");
//        data.setState("partialDeal,allDeal");
        ApiResponse response = orderService.orderHistory(data);
        System.out.println(response.getBody());
    }

}
