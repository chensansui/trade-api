package com.biex.api.client.service.bean;

import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

/**
 * @version : 1.0
 * @program : com.biex.api.client.trade.fund.bean
 * @discription : Leverage spot bean
 * @create : 2018-07-25-15
 **/
public class LeveragedSpotBean extends BiexObject {

    @ApiField("symbol")
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
