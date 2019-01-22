package com.biex.api.client.bean.sign;

import com.biex.api.client.mapping.ApiField;
import com.biex.api.client.mapping.JSONField;

import java.io.Serializable;
import java.util.Map;

/**
 * API basic response information。
 *
 * @author fengsheng
 */
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 5014379068811962022L;

    @ApiField("status")
    @JSONField(ordinal = 1)
    private String status;

    @ApiField("ts")
    @JSONField(ordinal = 2)
    private String ts;

    @ApiField("err-code")
    @JSONField(ordinal = 3)
    private String errorCode;

    @ApiField("err-msg")
    @JSONField(ordinal = 4)
    private String msg;

    @JSONField(ordinal = 5)
    private String body;

    @ApiField("data")
    private Object data;

    private Map<String, String> params;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
