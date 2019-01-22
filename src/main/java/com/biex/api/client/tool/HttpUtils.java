/*
 * @(#)HttpUtils.java 2015-4-14 下午1:46:50
 * Copyright 2015, Inc. All rights reserved. com.biex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.biex.api.client.tool;


import com.biex.api.client.constant.CharsetConst;
import org.apache.commons.collections.MapUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>File：HttpUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-14 下午1:46:50</p>
 *
 * @version 1.0
 */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static final RequestConfig defultConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();

    //
    private HttpUtils() {
        super();
    }

    /**
     * Get HttpClient
     *
     * @return HttpClient HttpClient
     */
    public static HttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient;
    }

    /**
     * Get HttpClient
     *
     * @return HttpClient HttpClient
     */
    public static CloseableHttpClient getHttpClient2() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient;
    }

    /**
     *       * Execute the Get request of Http
     *       * @param httpClient HttpClient
     *       * @param uri url address
     *       * @return String What the Http request returns
     *      
     */
    public static String get(HttpClient httpClient, String uri) {
        return get(httpClient, uri, null);
    }

    /**
     *       * Execute the Get request of Http
     *       * @param httpClient HttpClient
     *       * @param uri url address
     *       * @return String What the Http request returns
     *      
     */
    public static String get(HttpClient httpClient, RequestConfig config, String uri) {
        return get(httpClient, config, uri, null);
    }

    /**
     *       * Send get request
     *       * @param httpClient HttpClient
     *       * @param uri request address
     *       * @param map request parameter
     *       * @return String What is returned
     *      
     */
    public static String get(HttpClient httpClient, String uri, Map<String, String> map) {
        return get(httpClient, uri, map, null);
    }

    /**
     *       * Send get request
     *       * @param httpClient HttpClient
     *       * @param config
     *       * @param uri request address
     *       * @param map request parameter
     *       * @return String What is returned
     *      
     */
    public static String get(HttpClient httpClient, RequestConfig config, String uri, Map<String, String> map) {
        return get(httpClient, config, uri, map, null);
    }

    /**
     *      * GET submission, specifying the encoding
     *      * @param httpClient HttpClient
     *      * @param uri request address
     *      * @param charsetName encoding
     *      * @return String The content of the response
     *     
     */
    public static String get(HttpClient httpClient, String uri, Map<String, String> map, String charsetName) {
        return get(httpClient, uri, map, charsetName, null);
    }

    /**
     *      * GET submission, specifying the encoding
     *      * @param httpClient HttpClient
     *      * @param config
     *      * @param uri request address
     *      * @param charsetName encoding
     *      * @return String The content of the response
     *     
     */
    public static String get(HttpClient httpClient, RequestConfig config, String uri, Map<String, String> map, String charsetName) {
        return get(httpClient, config, uri, map, charsetName, null);
    }

    /**
     *       * Send get request
     *       * @param httpClient HttpClient
     *       * @param uri request address
     *       * @param map request parameter
     *       * @param charsetName request encoding
     *       * @param header header request header parameter
     *       * @return String What is returned
     *      
     */
    public static String get(HttpClient httpClient, String uri, Map<String, String> map, String charsetName, Map<String, String> header) {
        return get(httpClient, null, uri, map, charsetName, header);
    }

    /**
     *       * Send get request
     *       *
     *       * @param httpClient HttpClient
     *       * @param config
     *       * @param uri request address
     *       * @param map request parameter
     *       * @param charsetName request encoding
     *       * @param header header request header parameter
     *       * @return String What is returned
     *      
     */
    public static String get(HttpClient httpClient, RequestConfig config, String uri, Map<String, String> map, String charsetName, Map<String, String> header) {
        HttpGet httpGet = new HttpGet(uri);
        String parameter = getStringFromMap(map, charsetName);
        try {
            if (null != config) httpGet.setConfig(config);
            else httpGet.setConfig(defultConfig);
            if(StringUtils.isEmpty(parameter))
                httpGet.setURI(new URI(httpGet.getURI().toString()));
            else{
                if(uri.contains("?")) httpGet.setURI(new URI(httpGet.getURI().toString() + "&" + parameter));
                else httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + parameter));
            }
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
        return executeRequest(httpClient, httpGet, charsetName, header);
    }

    /**
     *       * Get method is submitted, the URL contains the query parameters, format: http://www.g.cn
     *       *
     *       * @param url submit address, for example: http://www.g.cn
     *       * @return String response message, Html source code for this address
     *      
     */
    public static String get(String url) {
        return get(getHttpClient(), url, null, null, null);
    }

    /**
     *       * Get method is submitted, the URL contains the query parameters, format: http://www.g.cn
     *       *
     *       * @param url submit address, for example: http://www.g.cn
     *       * @return String response message, Html source code for this address
     *      
     */
    public static String get(String url, Map<String, String> map) {
        return get(getHttpClient(), url, map, "UTF-8", null);
    }

    /**
     *       * Get method is submitted, the URL contains the query parameters, format: http://www.g.cn
     *       *
     *       * @param url submit address, for example: http://www.g.cn
     *       * @return String response message, Html source code for this address
     *      
     */
    public static String get(String url, RequestConfig config) {
        return get(getHttpClient(), config, url, null, null, null);
    }

    /**
     *       * Submit Http request
     *       *
     *       * @param httpClient HttpClient
     *       * @param request HttpRequestBase
     *       * @param charsetName encoding name
     *       * @param header header request header parameter
     *       * @return String What the Http request returns
     *      
     */
    private static String executeRequest(HttpClient httpClient, HttpRequestBase request, String charsetName, Map<String, String> header) {
        if (null == httpClient || null == request) {
            throw new NullPointerException("httpClient or HttpRequestBase is empty!");
        }
        String responseText = null;
        if (null != header) {
            for (Iterator<Entry<String, String>> it = header.entrySet().iterator(); it.hasNext(); ) {
                Entry<String, String> entry = it.next();
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
        try {
            HttpResponse response = httpClient.execute(request);
            // Get status
            int statuscode = response.getStatusLine().getStatusCode();
            // Redirect processing
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY) || (statuscode == HttpStatus.SC_SEE_OTHER)
                    || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header redirectLocation = response.getFirstHeader("Location");
                String newUri = redirectLocation.getValue();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(newUri)) {
                    request.setURI(new URI(newUri));
                    response = httpClient.execute(request);
                }
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(charsetName)) {
                responseText = EntityUtils.toString(response.getEntity(), charsetName);
            } else {
                responseText = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            releaseConnection(request);
        }
        return responseText;
    }

    /**
     *       * Send a post request
     *       *
     *       * @param httpClient HttpClient
     *       * @param uri request address
     *       * @return String Return content
     *      
     */
    public static String post(HttpClient httpClient, String uri) {
        return post(httpClient, uri, null);
    }

    /**
     *       * Send a post request
     *       *
     *       * @param httpClient HttpClient
     *       * @param uri request address
     *       * @return String Return content
     *      
     */
    public static String post(HttpClient httpClient, RequestConfig config, String uri) {
        return post(httpClient, config, uri, null);
    }

    /**
     *       * Post method submission, the URL contains the submission parameters, format: http://www.g.cn
     *       *
     *       * @param url submission address
     *       * @param params submit parameter set, key/value pairs
     *       * @return String response message
     *      
     */
    public static String post(String url, Map<String, String> params) {
        return post(getHttpClient(), url, params, null, null);
    }

    /**
     *       * Post method submission, the URL contains the submission parameters, format: http://www.g.cn
     *       *
     *       * @param url submission address
     *       * @param config
     *       * @param params submit parameter set, key/value pairs
     *       * @return String response message
     *      
     */
    public static String post(String url, RequestConfig config, Map<String, String> params) {
        return post(getHttpClient(), config, url, params, null, null);
    }

    /**
     *       * POST submission
     *       *
     *       * @param uri submission address
     *       * @param map Submitted parameter MAP
     *       * @return String The content of the response
     *      
     */
    public static String post(HttpClient httpClient, String uri, Map<String, String> map) {
        return post(httpClient, uri, map, null);
    }

    /**
     *       * POST submission
     *       *
     *       * @param httpClient
     *       * @param config
     *       * @param uri
     *       * @param map
     *       * @return
     *      
     */
    public static String post(HttpClient httpClient, RequestConfig config, String uri, Map<String, String> map) {
        return post(httpClient, config, uri, map, null);
    }

    /**
     *       * POST submission
     *       *
     *       * @param httpClient
     *       * @param uri
     *       * @param map
     *       * @param charsetName
     *       * @return
     *      
     */
    public static String post(HttpClient httpClient, String uri, Map<String, String> map, String charsetName) {
        return post(httpClient, uri, map, charsetName, null);
    }

    /**
     *       * POST submission
     *       *
     *       * @param httpClient
     *       * @param config
     *       * @param uri
     *       * @param map
     *       * @param charsetName
     *       * @return
     *      
     */
    public static String post(HttpClient httpClient, RequestConfig config, String uri, Map<String, String> map, String charsetName) {
        return post(httpClient, config, uri, map, charsetName, null);
    }

    /**
     *       * POST submits JSON data
     *       *
     *       * @param httpClient
     *       * @param url
     *       * @param json
     *       * @return
     *      
     */
    public static String postWithJSON(HttpClient httpClient, String url, String json) {
        return postWithJSON(httpClient, null, url, json);
    }

    /**
     *       * POST submits JSON data
     *       *
     *       * @param httpClient
     *       * @param config
     *       * @param url
     *       * @param json
     *       * @return
     *      
     */
    public static String postWithJSON(HttpClient httpClient, RequestConfig config, String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        if (null != config) httpPost.setConfig(config);
        else httpPost.setConfig(defultConfig);
        httpPost.setEntity(entity);
        return executeRequest(httpClient, httpPost, null, null);
    }

    /**
     *       * @param httpClient HttpClient
     *       * @param uri request address
     *       * @param map request parameter
     *       * @param charsetName request encoding
     *       * @param header Access to the road
     *       * @return String What the request returns
     *      
     */
    public static String post(HttpClient httpClient, String uri, Map<String, String> map, String charsetName, Map<String, String> header) {
        return post(httpClient, null, uri, map, charsetName, header);
    }

    /**
     *       * Send Http post request
     *       *
     *       * @param httpClient HttpClient
     *       * @param config request settings
     *       * @param uri request address
     *       * @param map request parameter
     *       * @param charsetName request encoding
     *       * @param header Access to the road
     *       * @return String What the request returns
     *      
     */
    public static String post(HttpClient httpClient, RequestConfig config, String uri, Map<String, String> map, String charsetName, Map<String, String> header) {
        HttpPost httpPost = new HttpPost(uri);
        HttpEntity httpEntity = getEntityFromMap(map, charsetName);
        if (null != httpEntity) {
            if (null != config) httpPost.setConfig(config);
            else httpPost.setConfig(defultConfig);
            httpPost.setEntity(httpEntity);
        }
        return executeRequest(httpClient, httpPost, charsetName, header);
    }

    /**
     *       * Get HTTPEntity based on the Map request parameter
     *       *
     *       * @param map Map<String, String> format request parameters
     *       * @param charsetName encoding name
     *       * @return HttpEntity HttpEntity
     *      
     */
    private static HttpEntity getEntityFromMap(Map<String, String> map, String charsetName) {
        List<NameValuePair> list = getListFromMap(map);
        return getEntityFromList(list, charsetName);
    }

    /**
     *       * Get the string of the get request format according to the Map request parameter
     *       *
     *       * @param map Map<String,String> request parameters
     *       * @param charsetName request encoding name
     *       * @return String get request format string
     *      
     */
    private static String getStringFromMap(Map<String, String> map, String charsetName) {
        String httpParameter = null;
        List<NameValuePair> list = getListFromMap(map);
        HttpEntity entity = getEntityFromList(list, charsetName);
        try {
            httpParameter = EntityUtils.toString(entity);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return httpParameter;
    }

    /**
     *       * Convert Map<String, String> request parameters to List<NameValuePair>
     *       *
     *       * @param map Map<String, String> request parameters
     *       * @return List<NameValuePair> List<NameValuePair>
     *      
     */
    private static List<NameValuePair> getListFromMap(Map<String, String> map) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if (!MapUtils.isEmpty(map)) {
            for (Iterator<Entry<String, String>> it = map.entrySet().iterator(); it.hasNext(); ) {
                Entry<String, String> entry = it.next();
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return list;
    }

    /**
     *       * Convert List<NameValuePair> to HttpEneity request parameter
     *       *
     *       * @param list List<NameValuePair>
     *       * @param charsetName request encoding
     *       * @return HttpEntity HttpEntity
     *      
     */
    private static HttpEntity getEntityFromList(List<NameValuePair> list, String charsetName) {
        UrlEncodedFormEntity entity = null;
        try {
            if (org.apache.commons.lang3.StringUtils.isBlank(charsetName)) {
                charsetName = CharsetConst.CHARSET_UT;
            }
            entity = new UrlEncodedFormEntity(list, charsetName);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    /**
     * Release request connection
     *
     * @param request HttpRequestBase
     */
    private static void releaseConnection(HttpRequestBase request) {
        if (request != null) {
            request.releaseConnection();
        }
    }

}
