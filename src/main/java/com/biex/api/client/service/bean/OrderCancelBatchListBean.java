package com.biex.api.client.service.bean;

import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.mapping.ApiField;

import java.util.List;

/**
 * @author : yukai
 * @version : 1.0
 * @program : com.biex.api.client.service.bean
 * @discription : order cancel batch bean list
 * @create : 2019-01-10-19
 **/
public class OrderCancelBatchListBean extends BiexObject {

    @ApiField("orderList")
    List<OrderCancelbatchBean> orderBeanList;

    public OrderCancelBatchListBean(List<OrderCancelbatchBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }

    public List<OrderCancelbatchBean> getOrderBeanList() {
        return orderBeanList;
    }

    public void setOrderBeanList(List<OrderCancelbatchBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }
}
