/**
 * biex.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.biex.api.client;


import com.biex.api.client.bean.sign.ApiBasicRequest;
import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.exception.ApiException;

/**
 * Standard client interface
 */
public interface ApiClient
{
    /**
     * Initiate a regular request
     * @param <T>
     * @param request
     * @return
     * @throws ApiException
     */
    <T extends ApiResponse> T execute(String requestType, ApiBasicRequest<T> request) throws ApiException;

    /**
     * Initiate a request with an authorization code
     * @param request
     * @param authToken
     * @return
     * @throws ApiException
     */
    <T extends ApiResponse> T execute(String requestType, ApiBasicRequest<T> request, String authToken) throws ApiException;
    
}
