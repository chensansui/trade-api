package com.biex.api.client.trade.fund.controller;

import com.biex.api.client.BiexServiceFactory;
import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.service.FundService;
import com.biex.api.client.service.bean.*;
import org.junit.Test;

/**
 * @version : 1.0
 * @discription : Asset test class
 * @create : 2018-07-07-14
 **/
public class TestFundController {

    //Use timestamp as AES key
    BiexServiceFactory factory = BiexServiceFactory.newInstance();

    FundService fundService = factory.newFundService();

    /**
     * Pure spot account
     *
     * @throws Exception
     */
    @Test
    public void testGetWalletAsset() {//ok
        PureSpotBean data = new PureSpotBean();
        data.setCurrency("usdt");
        ApiResponse response = fundService.getWalletAsset(data);
        System.out.println(response.getBody());
    }

    /**
     * Contract account information
     *
     * @throws Exception
     */
    @Test
    public void testGetContractAccountInfo() {//
        LeveragedSpotBean data = new LeveragedSpotBean();
//        data.setSymbol("ETH2USDT_LSWAP");
        data.setSymbol("BTC2USDT_LM");
        ApiResponse response = fundService.getContractAccountInfo(data);
        System.out.println(response.getBody());
    }

    /**
     * Contract account position
     *
     * @throws Exception
     */
    @Test
    public void testGetContractAccountPosition() {
        LeveragedSpotBean data = new LeveragedSpotBean();
//        data.setSymbol("ETH2USDT_LSWAP");
        data.setSymbol("BTC2USDT_LM");

        ApiResponse response = fundService.getContractAccountPosition(data);
        System.out.println(response.getBody());
    }

    /**
     * Coin withdrawal request
     *
     * @throws Exception
     */
    @Test
    public void testWithdraw() {
        WithdrawBean data = new WithdrawBean();
        //ETH
        data.setAddress("16gQ5FphhCgAXVQzZCePuwiYWFAmpEkEiS");
        data.setAmount("5");
        data.setCurrency("usdt");
        data.setFundPwd("960610");
        ApiResponse response = fundService.withdrawBiex(data);
        System.out.println(response.getBody());
    }

    /**
     * Coin cancellation
     *
     * @throws Exception
     */
    @Test
    public void testWithdrawCancel() {//ok
        WithdrawCancelBean data = new WithdrawCancelBean();
        data.setWithdrawId(181946157084323840L);
        data.setCurrency("usdt");
        ApiResponse response = fundService.withdrawCancel(data);
        System.out.println(response.getBody());
    }

    /**
     * Cash withdrawal record
     *
     * @throws Exception
     */
    @Test
    public void testWithdrawRecords() {//ok
        WithdrawRecordBean data = new WithdrawRecordBean();
        data.setCurrency("usdt");
        ApiResponse response = fundService.withdrawRecords(data);
        System.out.println(response.getBody());
    }

    /**
     * Deposit address
     */
    @Test
    public void testDepositAddress() {//ok
        WithdrawRecordBean data = new WithdrawRecordBean();
        data.setCurrency("usdt");
        ApiResponse response = fundService.depositAddress(data);
        System.out.println(response.getBody());
    }

    /**
     * Recharge record
     *
     * @throws Exception
     */
    @Test
    public void testDepositRecords() {//ok
        WithdrawRecordBean data = new WithdrawRecordBean();
        data.setCurrency("btc");
        ApiResponse response = fundService.depositRecords(data);
        System.out.println(response.getBody());
    }


    /**
     * Funding Fee Rate
     *
     * @throws Exception
     */
    @Test
    public void fundingFeeRate() {//ok
        FundFeeRateBean data = new FundFeeRateBean();
        data.setSymbol("BTC2USDT_LM");
        ApiResponse response = fundService.fundingFeeRate(data);
        System.out.println(response.getBody());
    }

    /**
     * insurance Fund
     *
     * @throws Exception
     */
    @Test
    public void insuranceFund() {//ok
        FundFeeRateBean data = new FundFeeRateBean();
        data.setSymbol("BTC2USDT_LM");
        ApiResponse response = fundService.insuranceFund(data);
        System.out.println(response.getBody());
    }

    /**
     * liquidation
     *
     * @throws Exception
     */
    @Test
    public void liquidation() {//ok
        FundFeeRateBean data = new FundFeeRateBean();
        data.setSymbol("BTC2USDT_LM");
        ApiResponse response = fundService.liquidation(data);
        System.out.println(response.getBody());
    }

    /**
     * settlement
     *
     * @throws Exception
     */
    @Test
    public void settlement() {//ok
        FundFeeRateBean data = new FundFeeRateBean();
        data.setSymbol("BTC2USDT_LM");
        ApiResponse response = fundService.settlement(data);
        System.out.println(response.getBody());
    }

}
