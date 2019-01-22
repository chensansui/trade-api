/**
 * biex.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.biex.api.client;


import com.biex.api.client.bean.sign.*;
import com.biex.api.client.constant.BiexConstants;
import com.biex.api.client.exception.ApiException;
import com.biex.api.client.tool.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * @version : 1.0
 * @discription : Test transaction interface
 * @create : 2018-07-05-14
 **/
public class DefaultApiClient implements ApiClient {
    private String serverUrl;

    private String privateKey;

    private String format = BiexConstants.FORMAT_JSON;

    private String sign_type = BiexConstants.SIGN_TYPE_SHA256;

    private String encryptType = BiexConstants.ENCRYPT_TYPE_AES;

    private String charset = BiexConstants.CHARSET_UTF8;

    private String api_key;

    private int connectTimeout = 3000;

    private int readTimeout = 15000;

    static {
        Security.setProperty("jdk.certpath.disabledAlgorithms", "");
    }

    /**
     * Default constructor
     *
     * @param serverUrl  server address
     * @param privateKey private key
     *                        
     */
    public DefaultApiClient(String serverUrl, String privateKey) {
        this.serverUrl = serverUrl;
        this.privateKey = privateKey;
    }

    /**
     * Construction method
     */
    public DefaultApiClient(String[] args) {
        this(args[0], args[1]);
        this.api_key = args[2];
    }

    /**
     * Initiate a regular request
     *
     * @param request
     * @param <T>
     * @return
     * @throws ApiException
     */
    public <T extends ApiResponse> T execute(String requestType, ApiBasicRequest<T> request) throws ApiException {
        requestType = requestType == null ? "GET" : requestType;
        return execute(requestType, request, null);
    }

    /**
     * Initiate a request with an authorization code
     *
     * @param request
     * @param authToken
     * @param <T>
     * @return
     * @throws ApiException
     */
    public <T extends ApiResponse> T execute(String requestType, ApiBasicRequest<T> request, String authToken) throws ApiException {
        ApiBexParser<T> parser = new ApiObjectJsonParser<T>(request.getResponseClass());
        return _execute(request, parser, authToken, requestType);
    }

    public Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        if (obj == null) return null;
        Map<String, String> map = new HashMap<>();
        Field[] declaredField = obj.getClass().getDeclaredFields();
        for (Field field : declaredField) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj) == null ? "" : String.valueOf(field.get(obj)));
        }
        return map;
    }

    /**
     * Assemble interface parameters, handle encryption, signature logic
     *
     * @param request
     * @param authToken
     * @return
     * @throws ApiException
     */
    private <T extends ApiResponse> RequestHolder getRequestHolderWithSign(ApiBasicRequest<?> request, String authToken) throws ApiException {
        RequestHolder requestHolder = new RequestHolder();
        BiexMap appParams = new BiexMap(request.getTextParams());
        // Serialize bizModel to fill bizContent only if the API contains the biz_content parameter and the value is empty
        try {
            if (request.getClass().getMethod("getContent") != null && StringUtils.isEmpty(appParams.get(BiexConstants.CONTENT_KEY)) && request.getBizModel() != null) {
                // 业务参数直接拼接在url后面
                appParams = new BiexMap(objectToMap(request.getBizModel()));
            }
        }
        catch (NoSuchMethodException e) {
            // Can't find getContent, do nothing
        }
        catch (SecurityException e) {
            BiexLogger.logBizError(e);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(authToken)) appParams.put(BiexConstants.AUTH_TOKEN, authToken);
        requestHolder.setApplicationParams(appParams);// Business request parameter
        if (StringUtils.isEmpty(charset)) charset = BiexConstants.CHARSET_UTF8;
        BiexMap protocalMustParams = new BiexMap();// Public required parameter
        protocalMustParams.put(BiexConstants.VERSION, request.getApiVersion());
        protocalMustParams.put(BiexConstants.SIGN_TYPE, this.sign_type);
//        protocalMustParams.put(BiexConstants.TERMINAL_TYPE, request.getTerminalType());
//        protocalMustParams.put(BiexConstants.TERMINAL_INFO, request.getTerminalInfo());
        protocalMustParams.put(BiexConstants.CHARSET, charset);
        protocalMustParams.put(BiexConstants.TIMESTAMP, System.currentTimeMillis());
        protocalMustParams.put("api_key", api_key);
        requestHolder.setProtocalMustParams(protocalMustParams);
        BiexMap protocalOptParams = new BiexMap();// Public optional parameter
        protocalOptParams.put(BiexConstants.FORMAT, format);
        requestHolder.setProtocalOptParams(protocalOptParams);
        if (!StringUtils.isEmpty(this.sign_type)) {// Add the signature to the public required parameter
            String signContent = HmacSHA256Signature.getSignatureContent(requestHolder);
            protocalMustParams.put(BiexConstants.SIGN, HmacSHA256Signature.sign_256(signContent, this.privateKey, this.charset));
        }
        else {
            protocalMustParams.put(BiexConstants.SIGN, "");
        }
        return requestHolder;
    }

    /**
     * Get the base url of the POST request
     *
     * @param requestHolder
     * @return
     * @throws ApiException
     */
    private String getRequestUrl(RequestHolder requestHolder) throws ApiException {
        StringBuffer urlSb = new StringBuffer(serverUrl);
        try {
            String sysMustQuery = WebUtils.buildQuery(requestHolder.getProtocalMustParams(), charset);
            String sysOptQuery = WebUtils.buildQuery(requestHolder.getProtocalOptParams(), charset);
            urlSb.append("?");
            urlSb.append(sysMustQuery);
            if (sysOptQuery != null & sysOptQuery.length() > 0) {
                urlSb.append("&");
                urlSb.append(sysOptQuery);
            }
        }
        catch (IOException e) {
            throw new ApiException(e);
        }
        return urlSb.toString();
    }

    /**
     * Get jump link in GET mode
     *
     * @param requestHolder
     * @return
     * @throws ApiException
     */
    private String getRedirectUrl(RequestHolder requestHolder) throws ApiException {
        StringBuffer urlSb = new StringBuffer(serverUrl);
        try {
            Map<String, String> sortedMap = HmacSHA256Signature.getSortedMap(requestHolder);
            String sortedQuery = WebUtils.buildQuery(sortedMap, charset);
            String sign = requestHolder.getProtocalMustParams().get(BiexConstants.SIGN);
            urlSb.append("?");
            urlSb.append(sortedQuery);
            if (sign != null & sign.length() > 0) {
                Map<String, String> signMap = new HashMap<String, String>();
                signMap.put(BiexConstants.SIGN, sign);
                String signQuery = WebUtils.buildQuery(signMap, charset);
                urlSb.append("&");
                urlSb.append(signQuery);
            }
        }
        catch (IOException e) {
            throw new ApiException(e);
        }
        return urlSb.toString();
    }

    private <T extends ApiResponse> T _execute(ApiBasicRequest<T> request, ApiBexParser<T> parser, String authToken, String requestType) throws ApiException {
        Map<String, Object> rt = doPost(request, authToken, requestType);
        if (null == rt) {
            return null;
        }
        T tRsp;
        try {
            ResponseEncryptItem responseItem = encryptResponse(rt);
            tRsp = parser.parse(responseItem.getRealContent());
            tRsp = JSONUtils.readValue(responseItem.getRealContent(), new TypeReference<T>() {
            });
            tRsp.setBody(responseItem.getRealContent());
            tRsp.setParams((BiexMap) rt.get("textParams"));
            return tRsp;
        }
        catch (RuntimeException e) {
            BiexLogger.logBizError((String) rt.get("rsp"));
            throw e;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * POST submission
     *
     * @param request
     * @param authToken
     * @return
     * @throws ApiException
     */
    private <T extends ApiResponse> Map<String, Object> doPost(ApiBasicRequest<T> request, String authToken, String requestType) throws ApiException {
        Map<String, Object> result = new HashMap<String, Object>();
        RequestHolder requestHolder = getRequestHolderWithSign(request, authToken);
        String url = getRequestUrl(requestHolder);
        BiexLogger.logBizDebug(getRedirectUrl(requestHolder));
        String rsp = "";
        try {
            if (request instanceof ApiUploadRequest) {
                ApiUploadRequest<T> uRequest = (ApiUploadRequest<T>) request;
                Map<String, FileItem> fileParams = BiexUtils.cleanupMap(uRequest.getFileParams());
                rsp = WebUtils.doPost(url, requestHolder.getApplicationParams(), fileParams, charset, connectTimeout, readTimeout);
            }
            else {
                if (requestType.equalsIgnoreCase("POST"))
                    rsp = WebUtils.doPost(url, requestHolder.getApplicationParams(), charset, connectTimeout, readTimeout);
                if (requestType.equalsIgnoreCase("GET"))
                    rsp = WebUtils.doGet(url, requestHolder.getApplicationParams(), charset, connectTimeout, readTimeout);
            }
        }
        catch (IOException e) {
            throw new ApiException(e);
        }
        result.put("rsp", rsp);
        result.put("textParams", requestHolder.getApplicationParams());
        result.put("protocalMustParams", requestHolder.getProtocalMustParams());
        result.put("protocalOptParams", requestHolder.getProtocalOptParams());
        result.put("url", url);
        return result;
    }

    /**
     * Decrypt response
     *
     * @param rt
     * @return
     * @throws ApiException
     */
    private ResponseEncryptItem encryptResponse(Map<String, Object> rt) throws ApiException {
        String responseBody = (String) rt.get("rsp");
        String realBody = (String) rt.get("rsp");
        return new ResponseEncryptItem(responseBody, realBody);
    }
}
