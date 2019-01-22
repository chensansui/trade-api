package com.biex.api.client.service.bean;


import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;
import com.biex.api.client.constant.BiexConst;

/**
 * @version : 1.0
 * @discription : Coin recording bean
 * @create : 2018-07-07-16
 **/
public class WithdrawRecordBean extends BiexObject {

    @ApiField("currency")
    private String currency;

    @ApiField("page")
    private Integer page = BiexConst.DEFAULT_CURRENT_PAGE;

    @ApiField("rows")
    private Integer rows = BiexConst.DEFAULT_PAGE_SIZE;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
