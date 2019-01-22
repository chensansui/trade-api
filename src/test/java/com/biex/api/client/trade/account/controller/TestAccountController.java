package com.biex.api.client.trade.account.controller;

import com.biex.api.client.BiexServiceFactory;
import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.service.AccountService;
import org.junit.Test;

/**
 * @version : 1.0
 * @discription : Account test class
 * @create : 2018-07-07-14
 **/
public class TestAccountController {

    //Use timestamp as AES key
    BiexServiceFactory factory = BiexServiceFactory.newInstance();

    AccountService accountService = factory.newAccountService();

    /**
     * Get user information
     *
     * @throws Exception
     */
    @Test
    public void testGetAccountInfo() {
        ApiResponse response = accountService.getAccountInfo();
        System.out.println(response.getBody());
    }
}
