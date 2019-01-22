package com.biex.api.client.service.bean;


import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

/**
 * @version : 1.0
 * @discription : Cancel the currency bean
 * @create : 2018-07-07-15
 **/
public class WithdrawCancelBean extends BiexObject {

    @ApiField("withdrawId")
    private Long withdrawId;

    @ApiField("currency")
    private String currency;

    public Long getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Long withdrawId) {
        this.withdrawId = withdrawId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
