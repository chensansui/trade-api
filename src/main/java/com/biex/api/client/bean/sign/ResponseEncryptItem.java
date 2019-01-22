/**
 * biex.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.biex.api.client.bean.sign;

import java.io.Serializable;

/**
 * 
 * 
 * @version $Id: EncryptReponseItem.java, v 0.1 2016-3-28 下午6:20:04 Exp $
 */
public class ResponseEncryptItem implements Serializable
{
    /**  */
    private static final long serialVersionUID = 6680775791485372169L;
    
    /**
     * Response return
     */
    private String            respContent;
    
    /**
     * Return after processing
     */
    private String            realContent;
    
    /**
     * @param respContent
     * @param realContent
     */
    public ResponseEncryptItem(String respContent, String realContent)
    {
        super();
        this.respContent = respContent;
        this.realContent = realContent;
    }
    
    /**
     * Getter method for property <tt>respContent</tt>.
     * 
     * @return property value of respContent
     */
    public String getRespContent()
    {
        return respContent;
    }
    
    /**
     * Getter method for property <tt>realContent</tt>.
     * 
     * @return property value of realContent
     */
    public String getRealContent()
    {
        return realContent;
    }
}
