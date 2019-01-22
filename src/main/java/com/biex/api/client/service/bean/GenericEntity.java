package com.biex.api.client.service.bean;

/**
 * @author : yukai
 * @version : 1.0
 * @program : com.biex.api.client.service.bean
 * @discription : GenericEntity
 * @create : 2019-01-04-17
 **/
public class GenericEntity {

    private String serverUrl;

    private String requestMethod;

    public GenericEntity(String serverUrl, String requestMethod) {
        this.serverUrl = serverUrl;
        this.requestMethod = requestMethod;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
