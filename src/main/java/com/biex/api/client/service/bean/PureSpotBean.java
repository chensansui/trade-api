package com.biex.api.client.service.bean;

import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

/**
 * @version : 1.0
 * @program : com.biex.api.client.trade.fund.bean
 * @discription : Pure spot bean
 * @create : 2018-07-25-15
 **/
public class PureSpotBean extends BiexObject {

    @ApiField("currency")
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
