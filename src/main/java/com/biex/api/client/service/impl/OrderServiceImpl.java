package com.biex.api.client.service.impl;

import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.constant.BiexConst;
import com.biex.api.client.constant.BiexConstants;
import com.biex.api.client.service.OrderService;
import com.biex.api.client.service.bean.*;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @version : 1.0
 * @program : com.biex.api.client.service.impl
 * @discription : order service
 * @create : 2018-08-20-14
 **/
public class OrderServiceImpl extends GenericServiceImpl implements OrderService {

    @Override
    public ApiResponse changeLeverage(ChangeLeverageBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/changeLeverage", BiexConstants.METHOD_POST);
        return this.execute(entity, data);
    }

    public ApiResponse doMatchRequest(EntrustBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/order", BiexConstants.METHOD_POST);
        return this.execute(entity, data);
    }

    public ApiResponse doMatchCancelRequest(EntrustCancelBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/order_cancel", BiexConstants.METHOD_POST);
        return this.execute(entity, data);
    }

    public ApiResponse doMatchCancelBatchRequest(OrderCancelbatchBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/order_cancel_batch", BiexConstants.METHOD_POST);
        return this.execute(entity, data);
    }

    @Override
    public ApiResponse doMatchCancelAllRequest(OrderCancelbatchBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/order_cancel_all", BiexConstants.METHOD_POST);
        return this.execute(entity, data);
    }

    public ApiResponse searchOrderInfo(OrderInfoBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/order_info", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    public ApiResponse orderDealInfo(OrderInfoBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/deal_info", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    public ApiResponse orderCurrent(OrderCurrentBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/order_current", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

    public ApiResponse orderHistory(OrderCurrentBean data) {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "order/order_history", BiexConstants.METHOD_GET);
        return this.execute(entity, data);
    }

}
