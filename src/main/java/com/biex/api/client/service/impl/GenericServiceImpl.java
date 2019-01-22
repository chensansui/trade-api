package com.biex.api.client.service.impl;

import com.biex.api.client.ApiClient;
import com.biex.api.client.DefaultApiClient;
import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.bean.test.ApiBiexRequest;
import com.biex.api.client.constant.BiexConst;
import com.biex.api.client.service.GenericService;
import com.biex.api.client.service.bean.GenericEntity;

/**
 * @author : yukai
 * @version : 1.0
 * @program : com.biex.api.client.service.impl
 * @discription : GenericServiceImpl
 * @create : 2019-01-04-17
 **/
public class GenericServiceImpl implements GenericService {

    @Override
    public ApiResponse execute(GenericEntity entity, BiexObject object) {
        String[] data1 = {entity.getServerUrl(), BiexConst.PRIKEY, BiexConst.APIKEY};
        ApiClient client = new DefaultApiClient(data1);
        ApiBiexRequest request = new ApiBiexRequest();
        request.setBizModel(object);
        ApiResponse response = client.execute(entity.getRequestMethod(), request);
        return response;
    }

    @Override
    public ApiResponse execute(GenericEntity entity) {
        String[] data1 = {entity.getServerUrl(), BiexConst.PRIKEY, BiexConst.APIKEY};
        ApiClient client = new DefaultApiClient(data1);
        ApiBiexRequest request = new ApiBiexRequest();
        ApiResponse response = client.execute(entity.getRequestMethod(), request);
        return response;
    }
}
