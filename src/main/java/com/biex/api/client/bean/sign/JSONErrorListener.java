package com.biex.api.client.bean.sign;

public interface JSONErrorListener
{
    void start(String text);
    
    void error(String message, int column);
    
    void end();
}
