package com.biex.api.client.service.bean;

import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

/**
 * @author : yukai
 * @version : 1.0
 * @program : com.biex.api.client.service.bean
 * @discription : change account leverage
 * @create : 2019-01-11-09
 **/
public class ChangeLeverageBean extends BiexObject{

    @ApiField("symbol")
    private String symbol;

    // only support [2, 5, 10, 20, 50, 100]
    @ApiField("lever")
    private Long lever;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getLever() {
        return lever;
    }

    public void setLever(Long lever) {
        this.lever = lever;
    }
}
