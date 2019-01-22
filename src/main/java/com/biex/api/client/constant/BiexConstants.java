/**
 * biex.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.biex.api.client.constant;

/**
 * API constant definition
 */
public class BiexConstants
{
    /**
     * sha256WithRsa algorithm request type
     */
    public static final String SIGN_TYPE_SHA256            = "HmacSHA256";
    
    public static final String SIGN_TYPE                 = "sign_type";
    
    public static final String SIGN_ALGORITHMS           = "SHA1WithRSA";
    
    public static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";
    
    public static final String ENCRYPT_TYPE_AES          = "AES";
    
    public static final String FORMAT                    = "format";
    
    public static final String TIMESTAMP                 = "timestamp";
    
    public static final String VERSION                   = "version";
    
    public static final String SIGN                      = "sign";
    
    public static final String AUTH_TOKEN                = "auth_token";
    
    public static final String TERMINAL_TYPE             = "terminal_type";
    
    public static final String TERMINAL_INFO             = "terminal_info";
    
    public static final String CHARSET                   = "charset";
    
    public static final String ENCRYPT_KEY               = "encrypt_key";
    
    public static final String CONTENT_KEY               = "content";
    
    /**  Date默认时区 **/
    public static final String DATE_TIMEZONE             = "GMT+8";
    
    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8              = "UTF-8";
    
    /** JSON 应格式 */
    public static final String FORMAT_JSON               = "json";
    
    /** 新版本节点后缀 */
    public static final String RESPONSE_KEY              = "data";

    /** POST 请求 */
    public static final String METHOD_POST              = "POST";

    /** GET 请求 */
    public static final String METHOD_GET              = "GET";

}
