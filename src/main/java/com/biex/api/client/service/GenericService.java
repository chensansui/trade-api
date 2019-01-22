package com.biex.api.client.service;

import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.bean.sign.BiexObject;
import com.biex.api.client.service.bean.GenericEntity;

/**
 * @author : yukai
 * @version : 1.0
 * @program : com.biex.api.client.service
 * @discription : GenericService
 * @create : 2019-01-04-17
 **/
public interface GenericService {

    ApiResponse execute(GenericEntity data, BiexObject object);

    ApiResponse execute(GenericEntity data);

}
