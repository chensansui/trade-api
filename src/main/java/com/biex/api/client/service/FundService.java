package com.biex.api.client.service;

import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.service.bean.*;

/**
 * @version : 1.0
 * @program : com.biex.api.rest.service
 * @discription : Fund Service
 * @create : 2018-08-20-13
 **/
public interface FundService extends GenericService {

    /**
     * Get wallet asset
     *
     * @param data
     * @return
     */
    ApiResponse getWalletAsset(PureSpotBean data);

    /**
     * contract account information
     *
     * @param data
     * @return
     */
    ApiResponse getContractAccountInfo(LeveragedSpotBean data);

    /**
     * contract account position
     *
     * @param data
     * @return
     */
    ApiResponse getContractAccountPosition(LeveragedSpotBean data);

    /**
     * Coin withdrawal request
     *
     * @param data
     * @return
     */
    ApiResponse withdrawBiex(WithdrawBean data);

    /**
     * Coin cancellation
     *
     * @param data
     * @return
     */
    ApiResponse withdrawCancel(WithdrawCancelBean data);

    /**
     * Cash withdrawal record
     *
     * @param data
     * @return
     */
    ApiResponse withdrawRecords(WithdrawRecordBean data);

    /**
     * Recharge address
     *
     * @param data
     * @return
     */
    ApiResponse depositAddress(BiexObject data);

    /**
     * Recharge record
     *
     * @param data
     * @return
     */
    ApiResponse depositRecords(WithdrawRecordBean data);

    /**
     * Fund fee rate
     *
     * @param data
     * @return
     */
    ApiResponse fundingFeeRate(FundFeeRateBean data);

    /**
     * insurance Fund
     *
     * @param data
     * @return
     */
    ApiResponse insuranceFund(FundFeeRateBean data);

    /**
     * liquidation orders
     *
     * @param data
     * @return
     */
    ApiResponse liquidation(FundFeeRateBean data);

    /**
     * settlement
     *
     * @param data
     * @return
     */
    ApiResponse settlement(FundFeeRateBean data);

}
