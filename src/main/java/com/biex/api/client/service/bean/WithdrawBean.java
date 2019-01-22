package com.biex.api.client.service.bean;


import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

import java.math.BigDecimal;

/**
 * @version : 1.0
 * @discription : Coin withdrawal requesting bean
 * @create : 2018-07-05-10
 **/
public class WithdrawBean extends BiexObject {

    @ApiField("address")
    private String address;

    @ApiField("currency")
    private String currency;

    @ApiField("amount")
    private String amount;

    @ApiField("fundPwd")
    private String fundPwd;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFundPwd() {
        return fundPwd;
    }

    public void setFundPwd(String fundPwd) {
        this.fundPwd = fundPwd;
    }
}
