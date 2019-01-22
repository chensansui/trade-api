package com.biex.api.client.bean.sign;


import com.biex.api.client.exception.ApiException;
import com.biex.api.client.mapping.BiexParser;

/**
 * a single JSON object interpreter。
 * 
 * @since 1.0, Apr 11, 2010
 */
public class ApiObjectJsonParser<T extends ApiResponse> implements ApiBexParser<T>
{
    private Class<T> clazz;

    public ApiObjectJsonParser(Class<T> clazz)
    {
        this.clazz = clazz;
    }
    
    public T parse(String rsp) throws ApiException
    {
        ApiConverter converter = new ApiJsonConverter();
        return converter.toResponse(rsp, clazz);
    }
    
    public Class<T> getResponseClass()
    {
        return clazz;
    }
    
    /** 
     * @see BiexParser#getSignItem(BasicRequest, String)
     */
    public SignItem getSignItem(ApiBasicRequest<?> request, String responseBody) throws ApiException
    {
        ApiConverter converter = new ApiJsonConverter();
        return converter.getSignItem(request, responseBody);
    }
    
    /** 
     * @see BiexParser#encryptSourceData(BasicRequest, String, String, String, String, String)
     */
    public String encryptSourceData(ApiBasicRequest<?> request, String body, String format, String encryptType, String encryptKey, String charset) throws ApiException
    {
        ApiConverter converter = new ApiJsonConverter();
        return converter.encryptSourceData(request, body, format, encryptType, encryptKey, charset);
    }
}
