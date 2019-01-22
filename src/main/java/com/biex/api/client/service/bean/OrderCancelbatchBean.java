package com.biex.api.client.service.bean;

import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

import java.util.List;

/**
 * @version : 1.0
 * @discription : Multiple undo beans
 * @create : 2018-07-07-16
 **/
public class OrderCancelbatchBean extends BiexObject{

    @ApiField("orderIds")
    private List<Long> orderIds;

    @ApiField("symbol")
    private String symbol;

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
