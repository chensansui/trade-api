package com.biex.api.client.service.bean;


import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

import java.math.BigDecimal;

/**
 * @version : 1.0
 * @discription : Order bean
 * @create : 2018-07-07-16
 **/
public class EntrustBean extends BiexObject {

    /**
     * Transaction pair
     */
    @ApiField("symbol")
    private String symbol;

    /**
     * Entrust type, only support limitPrice or marketPrice
     */
    @ApiField("type")
    private String type;

    /**
     * Commission side
     */
    @ApiField("side")
    private String side;

    /**
     * Number of orders
     */
    @ApiField("amount")
    private BigDecimal amount;

    /**
     * Commission price
     */
    @ApiField("price")
    private BigDecimal price;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
