package com.biex.api.client.service.impl;

import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.constant.BiexConst;
import com.biex.api.client.constant.BiexConstants;
import com.biex.api.client.service.AccountService;
import com.biex.api.client.service.bean.GenericEntity;

/**
 * @version : 1.0
 * @program : com.biex.api.client.service.impl
 * @discription : account service
 * @create : 2018-08-20-14
 **/
public class AccountServiceImpl extends GenericServiceImpl implements AccountService {

    public ApiResponse getAccountInfo() {
        GenericEntity entity = new GenericEntity(BiexConst.ROOT + "account/accountInfo", BiexConstants.METHOD_GET);
        return this.execute(entity);
    }
}
