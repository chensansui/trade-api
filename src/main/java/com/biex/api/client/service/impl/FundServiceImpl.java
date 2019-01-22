package com.biex.api.client.service.impl;

import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.constant.BiexConst;
import com.biex.api.client.constant.BiexConstants;
import com.biex.api.client.service.FundService;
import com.biex.api.client.service.bean.*;

/**
 * @version : 1.0
 * @program : com.biex.api.rest.service.impl
 * @discription : Fund Service
 * @create : 2018-08-20-13
 **/
public class FundServiceImpl extends GenericServiceImpl implements FundService {

    @Override
    public ApiResponse getWalletAsset(PureSpotBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/walletAsset", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse getContractAccountInfo(LeveragedSpotBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/contractAccountInfo", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse getContractAccountPosition(LeveragedSpotBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/contractAccountPosition", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse withdrawBiex(WithdrawBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/withdraw", BiexConstants.METHOD_POST);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse withdrawCancel(WithdrawCancelBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/withdraw_cancel", BiexConstants.METHOD_POST);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse withdrawRecords(WithdrawRecordBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/withdraw_records", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse depositAddress(BiexObject data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/deposit_address", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse depositRecords(WithdrawRecordBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/deposit_records", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse fundingFeeRate(FundFeeRateBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/fundingFeeRate", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse insuranceFund(FundFeeRateBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/insuranceFund", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse liquidation(FundFeeRateBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/liquidation", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse settlement(FundFeeRateBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "fund/settlement", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

}
