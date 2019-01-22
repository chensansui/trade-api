package com.biex.api.client.bean.sign;

import com.biex.api.client.constant.BiexConstants;
import com.biex.api.client.exception.ApiException;
import com.biex.api.client.mapping.ApiConverters;
import com.biex.api.client.mapping.Converter;
import com.biex.api.client.tool.Encrypt;
import com.biex.api.client.tool.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON format converter.
 * 
 * @since 1.0, Apr 11, 2010
 */
public class JsonConverter implements Converter
{
    public <T extends BiexResponse> T toResponse(String rsp, Class<T> clazz) throws ApiException
    {
        JSONReader reader = new JSONValidatingReader(new ExceptionErrorListener());
        Object rootObj = reader.read(rsp);
        if (rootObj instanceof Map<?, ?>)
        {
            Map<?, ?> rootJson = (Map<?, ?>) rootObj;
            return fromJson(rootJson, clazz);
        }
        return null;
    }

    /**
      * Convert data in JSON format to objects.
      *
      * @param <T> generic domain object
      * @param json data in JSON format
      * @param clazz generic field type
      * @return domain object
      * @throws ApiException
      */
    public <T> T fromJson(final Map<?, ?> json, Class<T> clazz) throws ApiException
    {
        return ApiConverters.convert(clazz, new Reader()
        {
            public boolean hasReturnField(Object name)
            {
                return json.containsKey(name);
            }
            
            public Object getPrimitiveObject(Object name)
            {
                return json.get(name);
            }
            
            public Object getObject(Object name, Class<?> type) throws ApiException
            {
                Object tmp = json.get(name);
                if (tmp instanceof Map<?, ?>)
                {
                    Map<?, ?> map = (Map<?, ?>) tmp;
                    return fromJson(map, type);
                }
                else
                {
                    return null;
                }
            }
            
            public List<?> getListObjects(Object listName, Object itemName, Class<?> subType) throws ApiException
            {
                List<Object> listObjs = null;
                Object listTmp = json.get(listName);
                if (listTmp instanceof Map<?, ?>)
                {
                    Map<?, ?> jsonMap = (Map<?, ?>) listTmp;
                    Object itemTmp = jsonMap.get(itemName);
                    if (itemTmp == null && listName != null)
                    {
                        String listNameStr = listName.toString();
                        itemTmp = jsonMap.get(listNameStr.substring(0, listNameStr.length() - 1));
                    }
                    if (itemTmp instanceof List<?>)
                    {
                        listObjs = getListObjectsInner(subType, itemTmp);
                    }
                }
                else if (listTmp instanceof List<?>)
                {
                    listObjs = getListObjectsInner(subType, listTmp);
                }
                return listObjs;
            }
            
            private List<Object> getListObjectsInner(Class<?> subType, Object itemTmp) throws ApiException
            {
                List<Object> listObjs;
                listObjs = new ArrayList<Object>();
                List<?> tmpList = (List<?>) itemTmp;
                for (Object subTmp : tmpList)
                {
                    Object obj = null;
                    if (String.class.isAssignableFrom(subType))
                    {
                        obj = subTmp;
                    }
                    else if (Long.class.isAssignableFrom(subType))
                    {
                        obj = subTmp;
                    }
                    else if (Integer.class.isAssignableFrom(subType))
                    {
                        obj = subTmp;
                    }
                    else if (Boolean.class.isAssignableFrom(subType))
                    {
                        obj = subTmp;
                    }
                    else if (subTmp instanceof Map<?, ?>)
                    {// object
                        Map<?, ?> subMap = (Map<?, ?>) subTmp;
                        obj = fromJson(subMap, subType);
                    }
                    if (obj != null)
                    {
                        listObjs.add(obj);
                    }
                }
                return listObjs;
            }
        });
    }
    
    /**
     * @see Converter#getSignItem(BasicRequest, String)
     */
    public SignItem getSignItem(BasicRequest<?> request, String responseBody) throws ApiException
    {
        // 响应为空则直接返回
        if (StringUtils.isEmpty(responseBody)) { return null; }
        SignItem signItem = new SignItem();
        // 获取签名
        String sign = getSign(responseBody);
        signItem.setSign(sign);
        // 签名源串
        String signSourceData = getSignSourceData(request, responseBody);
        signItem.setSignSourceDate(signSourceData);
        return signItem;
    }
    
    /**
     *
     * @param request
     * @param body
     * @return
     */
    private String getSignSourceData(BasicRequest<?> request, String body)
    {
        // 加签源串起点
        String rootNode = BiexConstants.RESPONSE_KEY;
        int indexOfRootNode = body.indexOf(rootNode);
        // 成功或者新版接口
        if (indexOfRootNode > 0)
        {
            return parseSignSourceData(body, rootNode, indexOfRootNode);
            // 老版本失败接口
        }
        else
        {
            return null;
        }
    }
    
    /**
     *   Get the signature source string content
     *
     * @param body
     * @param rootNode
     * @param indexOfRootNode
     * @return
     */
    private String parseSignSourceData(String body, String rootNode, int indexOfRootNode)
    {
        // 第一个字母+长度+引号和分号
        int signDataStartIndex = indexOfRootNode + rootNode.length() + 2;
        int indexOfSign = body.indexOf("\"" + BiexConstants.SIGN + "\"");
        if (indexOfSign < 0) { return null; }
        // 签名前-逗号
        int signDataEndIndex = indexOfSign - 1;
        return body.substring(signDataStartIndex, signDataEndIndex);
    }
    
    /**
     * Get signature
     *
     * @param body
     * @return
     */
    private String getSign(String body)
    {
        JSONReader reader = new JSONValidatingReader(new ExceptionErrorListener());
        Object rootObj = reader.read(body);
        Map<?, ?> rootJson = (Map<?, ?>) rootObj;
        return (String) rootJson.get(BiexConstants.SIGN);
    }
    
    /**
     * @see Converter#encryptSourceData(BasicRequest, String, String, String, String, String)
     */
    public String encryptSourceData(BasicRequest<?> request, String body, String format, String encryptType, String encryptKey, String charset) throws ApiException
    {
        ResponseParseItem respSignSourceData = getJSONSignSourceData(request, body);
        String bodyIndexContent = body.substring(0, respSignSourceData.getStartIndex());
        String bodyEndContent = body.substring(respSignSourceData.getEndIndex());
        return bodyIndexContent + Encrypt.decryptContent(respSignSourceData.getEncryptContent(), encryptType, encryptKey) + bodyEndContent;
    }
    
    /**
     *  Get the JSON response tag content string
     *
     * @param request
     * @param body
     * @return
     */
    private ResponseParseItem getJSONSignSourceData(BasicRequest<?> request, String body)
    {
        String rootNode = BiexConstants.RESPONSE_KEY;
        int indexOfRootNode = body.indexOf(rootNode);
        if (indexOfRootNode > 0)
        {
            return parseJSONSignSourceData(body, rootNode, indexOfRootNode);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * 
     * Intercept the return content
     * @param body
     * @param rootNode
     * @param indexOfRootNode
     * @return
     */
    private ResponseParseItem parseJSONSignSourceData(String body, String rootNode, int indexOfRootNode)
    {
        int signDataStartIndex = indexOfRootNode + rootNode.length() + 2;
        int indexOfSign = body.indexOf("\"message\"");
        if (indexOfSign < 0)
        {
            indexOfSign = body.length();
        }
        int signDataEndIndex = indexOfSign - 1;
        String encryptContent = body.substring(signDataStartIndex + 1, signDataEndIndex - 1);
        return new ResponseParseItem(signDataStartIndex, signDataEndIndex, encryptContent);
    }
}
