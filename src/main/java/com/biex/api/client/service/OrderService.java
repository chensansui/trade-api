package com.biex.api.client.service;

import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.service.bean.*;

/**
 * @version : 1.0
 * @program : com.biex.api.client.service
 * @discription : order service
 * @create : 2018-08-20-14
 **/
public interface OrderService extends GenericService {

    /**
     * Single order
     * @param data
     * @return
     */
    ApiResponse changeLeverage(ChangeLeverageBean data);

    /**
     * Single order
     * @param data
     * @return
     */
    ApiResponse doMatchRequest(EntrustBean data);

    /**
     * Transaction cancellation (single stroke)
     * @param data
     * @return
     */
    ApiResponse doMatchCancelRequest(EntrustCancelBean data);

    /**
     * Transaction cancellation (multiple)
     * @param data
     * @return
     */
    ApiResponse doMatchCancelBatchRequest(OrderCancelbatchBean data);
    /**
     * Transaction cancellation (multiple)
     * @param data
     * @return
     */
    ApiResponse doMatchCancelAllRequest(OrderCancelbatchBean data);

    /**
     * Query order details
     * @param data
     * @return
     */
    ApiResponse searchOrderInfo(OrderInfoBean data);

    /**
     * Query order transaction details
     * @param data
     * @return
     */
    ApiResponse orderDealInfo(OrderInfoBean data);

    /**
     * Current delegation list
     * @param data
     * @return
     */
    ApiResponse orderCurrent(OrderCurrentBean data);

    /**
     * Historical delegation list
     * @param data
     * @return
     */
    ApiResponse orderHistory(OrderCurrentBean data);
}
