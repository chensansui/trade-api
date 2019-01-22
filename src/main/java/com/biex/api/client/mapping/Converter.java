package com.biex.api.client.mapping;


import com.biex.api.client.bean.sign.BasicRequest;
import com.biex.api.client.bean.sign.BiexResponse;
import com.biex.api.client.bean.sign.SignItem;
import com.biex.api.client.exception.ApiException;

/**
  * Dynamic format converter.
  *
  * @since 1.0, Apr 11, 2010
  */
public interface Converter
{
    /**
      * Convert a string to a response object.
      *
      * @param <T> domain generics
      * @param rsp response string
      * @param clazz domain type
      * @return response object
      * @throws ApiException
      */
    <T extends BiexResponse> T toResponse(String rsp, Class<T> clazz) throws ApiException;

    /**
      * Get the signature data in the response
      *
      * @param request
      * @param responseBody
      * @return
      * @throws ApiException
      */
    SignItem getSignItem(BasicRequest<?> request, String responseBody) throws ApiException;

    /**
      * Get the real content in the decrypted response
      *
      * @param request
      * @param body
      * @param format
      * @param encryptType
      * @param encryptKey
      * @param charset
      * @return
      * @throws ApiException
      */
    String encryptSourceData(BasicRequest<?> request, String body, String format, String encryptType, String encryptKey, String charset) throws ApiException;
}
