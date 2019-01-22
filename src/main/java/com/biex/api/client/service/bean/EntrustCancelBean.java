package com.biex.api.client.service.bean;


import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

/**
 * @version : 1.0
 * @discription : Transaction cancellation
 * @create : 2018-07-07-16
 **/
public class EntrustCancelBean extends BiexObject {

    /**
     * Order ID
     */
    @ApiField("orderId")
    private Long orderId;

    /**
     * Transaction pair
     */
    @ApiField("symbol")
    private String symbol;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
