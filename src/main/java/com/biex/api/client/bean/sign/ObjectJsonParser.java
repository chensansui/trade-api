package com.biex.api.client.bean.sign;

import com.biex.api.client.exception.ApiException;
import com.biex.api.client.mapping.BiexParser;
import com.biex.api.client.mapping.Converter;

/**
 * A single JSON object interpreter.
 * 
 * @since 1.0, Apr 11, 2010
 */
public class ObjectJsonParser<T extends BiexResponse> implements BiexParser<T>
{
    private Class<T> clazz;
    
    public ObjectJsonParser(Class<T> clazz)
    {
        this.clazz = clazz;
    }
    
    public T parse(String rsp) throws ApiException
    {
        Converter converter = new JsonConverter();
        return converter.toResponse(rsp, clazz);
    }
    
    public Class<T> getResponseClass()
    {
        return clazz;
    }
    
    /** 
     * @see BiexParser#getSignItem(BasicRequest, String)
     */
    public SignItem getSignItem(BasicRequest<?> request, String responseBody) throws ApiException
    {
        Converter converter = new JsonConverter();
        return converter.getSignItem(request, responseBody);
    }
    
    /** 
     * @see BiexParser#encryptSourceData(BasicRequest, String, String, String, String, String)
     */
    public String encryptSourceData(BasicRequest<?> request, String body, String format, String encryptType, String encryptKey, String charset) throws ApiException
    {
        Converter converter = new JsonConverter();
        return converter.encryptSourceData(request, body, format, encryptType, encryptKey, charset);
    }
}
