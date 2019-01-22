package com.biex.api.client.service;

import com.biex.api.client.bean.sign.ApiResponse;

/**
 * @version : 1.0
 * @program : com.biex.api.client.service
 * @discription : account service
 * @create : 2018-08-20-14
 **/
public interface AccountService extends GenericService {

    /**
     * Get user information
     *
     * @return
     */
    ApiResponse getAccountInfo();

}
