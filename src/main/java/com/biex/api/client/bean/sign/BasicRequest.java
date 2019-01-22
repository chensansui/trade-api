package com.biex.api.client.bean.sign;

import java.util.Map;

/**
 * Request interface。
 * 
 * @since 1.0, Sep 12, 2009
 */
public interface BasicRequest<T extends BiexResponse>
{
    /**
      * Get all the set of text request parameters in the form of Key-Value. among them:
      * <ul>
      * <li>Key: Request parameter name</li>
      * <li>Value: request parameter value</li>
      * </ul>
      *
      * @return text request parameter set
      */
    Map<String, String> getTextParams();

    /**
      * Get the version of the current interface
      *
      * @return API version
      */
    String getApiVersion();

    /**
      * Set the version information of the current API
      *
      * @param apiVersion API version
      */
    void setApiVersion(String apiVersion);

    /**
      * Get terminal type
      *
      * @return terminal type
      */
    String getTerminalType();

    /**
      * Set the terminal type
      *
      * @param terminalType terminal type
      */
    void setTerminalType(String terminalType);

    /**
      * Get terminal information
      *
      * @return terminal information
      */
    String getTerminalInfo();

    /**
      * Set terminal information
      *
      * @param terminalInfo terminal information
      */
    void setTerminalInfo(String terminalInfo);

    /**
      * Get the response type of the current API
      *
      * @return response type
      */
    Class<T> getResponseClass();

    /**
      * Determine if encryption is required
      *
      * @return
      */
    boolean isNeedEncrypt();

    /**
      * Set whether the request requires encryption
      *
      * @param needEncrypt
      */
    void setNeedEncrypt(boolean needEncrypt);

    /**
      * Get business entity
      * @return
      */
    BiexObject getBizModel();

    /**
      * Set the business entity, if you want to use this method, please do not set biz_content directly
      *
      * @param bizModel
      */
    void setBizModel(BiexObject bizModel);
}
