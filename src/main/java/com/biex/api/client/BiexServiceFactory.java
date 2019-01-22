package com.biex.api.client;

import com.biex.api.client.service.AccountService;
import com.biex.api.client.service.FundService;
import com.biex.api.client.service.OrderService;
import com.biex.api.client.service.impl.AccountServiceImpl;
import com.biex.api.client.service.impl.FundServiceImpl;
import com.biex.api.client.service.impl.OrderServiceImpl;

/**
 * @version : 1.0
 * @program : com.biex.api.client
 * @discription : service factory
 * @create : 2018-08-20-15
 **/
public class BiexServiceFactory {

    /**
     * New instance.
     *
     * @return
     */
    public static BiexServiceFactory newInstance() {
        return new BiexServiceFactory();
    }

    /**
     * Creates a new fund service.
     */
    public FundService newFundService() {
        return new FundServiceImpl();
    }

    /**
     * Creates a new account service.
     */
    public AccountService newAccountService() {
        return new AccountServiceImpl();
    }

    /**
     * Creates a new order service.
     */
    public OrderService newOrderService() {
        return new OrderServiceImpl();
    }


}
