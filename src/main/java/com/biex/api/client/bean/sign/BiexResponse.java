package com.biex.api.client.bean.sign;

import com.biex.api.client.mapping.ApiField;

import java.io.Serializable;
import java.util.Map;

/**
 * API basic response information.
 * 
 * @author fengsheng
 */
public class BiexResponse implements Serializable
{
    private static final long   serialVersionUID = 5014379068811962022L;
    
    @ApiField("code")
    private String              code;
    
    @ApiField("msg")
    private String              msg;

    private String              body;

    private Map<String, String> params;

    /**
     * Discard method, please use getCode to replace
     *
     * @return
     */
    @Deprecated
    public String getErrorCode()
    {
        return this.getCode();
    }

    /**
     * Discard method, please use setCode to replace
     *
     * @param errorCode
     */
    @Deprecated
    public void setErrorCode(String errorCode)
    {
        this.setCode(errorCode);
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }


    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public Map<String, String> getParams()
    {
        return params;
    }

    public void setParams(Map<String, String> params)
    {
        this.params = params;
    }

}
