package com.biex.api.client.bean.test;


import com.biex.api.client.bean.sign.ApiBasicRequest;
import com.biex.api.client.bean.sign.ApiResponse;
import com.biex.api.client.bean.sign.BiexMap;
import com.biex.api.client.bean.sign.BiexObject;

import java.util.Map;

/**
 * Basic request object
 * 
 * @author auto create
 * @since 1.0, 2017-07-20 10:41:44
 */
public class ApiBiexRequest implements ApiBasicRequest<ApiResponse>
{
    private String      apiVersion  = "1.0";
    
    /**
     * add user-defined text parameters
     */
    private BiexMap udfParams;

    /**
     * terminal type
     */
    private String      terminalType;
    
    /**
     * Terminal information
     */
    private String      terminalInfo;

    /**
     * Business object
     */
    private BiexObject bizModel    = null;
    
    /**
     * Request content
     */
    private String      content;
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getApiVersion()
    {
        return this.apiVersion;
    }
    
    public void setApiVersion(String apiVersion)
    {
        this.apiVersion = apiVersion;
    }
    
    public void setTerminalType(String terminalType)
    {
        this.terminalType = terminalType;
    }
    
    public String getTerminalType()
    {
        return this.terminalType;
    }
    
    public void setTerminalInfo(String terminalInfo)
    {
        this.terminalInfo = terminalInfo;
    }
    
    public String getTerminalInfo()
    {
        return this.terminalInfo;
    }


    public Map<String, String> getTextParams()
    {
        BiexMap txtParams = new BiexMap();
        txtParams.put("content", this.content);
        if (udfParams != null)
        {
            txtParams.putAll(this.udfParams);
        }
        return txtParams;
    }

    public BiexMap getUdfParams() {
        return udfParams;
    }

    public void setUdfParams(BiexMap udfParams) {
        this.udfParams = udfParams;
    }

    public Class<ApiResponse> getResponseClass()
    {
        return ApiResponse.class;
    }

    public BiexObject getBizModel()
    {
        return this.bizModel;
    }
    
    public void setBizModel(BiexObject bizModel)
    {
        this.bizModel = bizModel;
    }
}
