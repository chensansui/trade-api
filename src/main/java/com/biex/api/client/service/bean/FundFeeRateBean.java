package com.biex.api.client.service.bean;

import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.constant.BiexConst;
import com.biex.api.client.mapping.ApiField;

/**
 * @author : yukai
 * @version : 1.0
 * @program : com.biex.api.client.service.bean
 * @discription : funding fee rate
 * @create : 2019-01-07-10
 **/
public class FundFeeRateBean extends BiexObject{

    @ApiField("symbol")
    private String symbol;

    @ApiField("page")
    private Integer page = BiexConst.DEFAULT_CURRENT_PAGE;

    @ApiField("rows")
    private Integer rows = BiexConst.DEFAULT_PAGE_SIZE;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
