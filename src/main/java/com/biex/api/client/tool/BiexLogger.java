package com.biex.api.client.tool;


import com.biex.api.client.bean.sign.BiexMap;
import com.biex.api.client.bean.sign.BiexResponse;
import com.biex.api.client.constant.BiexConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Jdk14Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

/**
  * Client log
  * Communication error format: time^_^sdk^_^app^_^ip^_^os^_^sdk^_^url^responseCode
  * Business error format: time^_^response
  */
public class BiexLogger
{
    private static final Log clog             = LogFactory.getLog("sdk.comm.err");
    
    private static final Log blog             = LogFactory.getLog("sdk.biz.err");
    
    private static String    osName           = System.getProperties().getProperty("os.name");
    
    private static String    ip               = null;
    
    private static boolean   needEnableLogger = true;
    
    public static void setNeedEnableLogger(boolean needEnableLogger)
    {
        BiexLogger.needEnableLogger = needEnableLogger;
    }
    
    public static String getIp()
    {
        if (ip == null)
        {
            try
            {
                ip = InetAddress.getLocalHost().getHostAddress();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return ip;
    }
    
    public static void setIp(String ip)
    {
        BiexLogger.ip = ip;
    }

    /**
      * Communication error log
      */
    public static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, byte[] content)
    {
        if (!needEnableLogger) { return; }
        String contentString = null;
        try
        {
            contentString = new String(content, "UTF-8");
            logCommError(e, conn, appKey, method, contentString);
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }
    
    /**
     * Communication error log
     */
    public static void logCommError(Exception e, String url, String appKey, String method, byte[] content)
    {
        if (!needEnableLogger) { return; }
        String contentString = null;
        try
        {
            contentString = new String(content, "UTF-8");
            logCommError(e, url, appKey, method, contentString);
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
    }
    
    /**
     * Communication error log
     */
    public static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, Map<String, String> params)
    {
        if (!needEnableLogger) { return; }
        _logCommError(e, conn, null, appKey, method, params);
    }
    
    public static void logCommError(Exception e, String url, String appKey, String method, Map<String, String> params)
    {
        if (!needEnableLogger) { return; }
        _logCommError(e, null, url, appKey, method, params);
    }
    
    /**
     * Communication error log
     */
    private static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, String content)
    {
        Map<String, String> params = parseParam(content);
        _logCommError(e, conn, null, appKey, method, params);
    }
    
    /**
     * Communication error log
     */
    private static void logCommError(Exception e, String url, String appKey, String method, String content)
    {
        Map<String, String> params = parseParam(content);
        _logCommError(e, null, url, appKey, method, params);
    }
    
    /**
     * Communication error log
     */
    private static void _logCommError(Exception e, HttpURLConnection conn, String url, String appKey, String method, Map<String, String> params)
    {
        String urlStr = null;
        String rspCode = "";
        if (conn != null)
        {
            try
            {
                urlStr = conn.getURL().toString();
                rspCode = "HTTP_ERROR_" + conn.getResponseCode();
            }
            catch (IOException ioe)
            {
            }
        }
        else
        {
            urlStr = url;
            rspCode = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(new Date().getTime());// 时间
        sb.append("^_^");
        sb.append(method);// API
        sb.append("^_^");
        sb.append(appKey);// APP
        sb.append("^_^");
        sb.append(getIp());// IP地址
        sb.append("^_^");
        sb.append(osName);// 操作系统
        sb.append("^_^");
        sb.append(urlStr);// 请求URL
        sb.append("^_^");
        sb.append(rspCode);
        sb.append("^_^");
        sb.append((e.getMessage() + "").replaceAll("\r\n", " "));
        clog.error(sb.toString());
    }
    
    private static Map<String, String> parseParam(String contentString)
    {
        Map<String, String> params = new HashMap<String, String>();
        if (contentString == null || contentString.trim().equals("")) { return params; }
        String[] paramsArray = contentString.split("\\&");
        if (paramsArray != null)
        {
            for (String param : paramsArray)
            {
                String[] keyValue = param.split("=");
                if (keyValue != null && keyValue.length == 2)
                {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }
    
    /**
     * Business / System Error Log
     */
    public static void logBizDebug(String rsp)
    {
        if (!needEnableLogger) { return; }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(BiexConstants.DATE_TIMEZONE));
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(new Date()));
        sb.append("^_^");
        sb.append(rsp);
        if (blog.isDebugEnabled())
        {
            blog.debug(sb.toString());
        }
    }
    
    /**
     * Business / System Error Log
     */
    public static void logBizError(String rsp)
    {
        if (!needEnableLogger) { return; }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(BiexConstants.DATE_TIMEZONE));
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(new Date()));
        sb.append("^_^");
        sb.append(rsp);
        blog.error(sb.toString());
    }
    
    /**
     * Business / System Error Log
     */
    public static void logBizError(Throwable t)
    {
        if (!needEnableLogger) { return; }
        blog.error(t);
    }
    
    /**
     * Record the complete error site when a special error occurs
     */
    public static void logErrorScene(Map<String, Object> rt, BiexResponse tRsp, String appSecret)
    {
        if (!needEnableLogger) { return; }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(BiexConstants.DATE_TIMEZONE));
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorScene");
        sb.append("^_^");
        sb.append(tRsp.getCode());
        sb.append("^_^");
        sb.append(ip);
        sb.append("^_^");
        sb.append(osName);
        sb.append("^_^");
        sb.append(df.format(new Date()));
        sb.append("^_^");
        sb.append("ProtocalMustParams:");
        appendLog((BiexMap) rt.get("protocalMustParams"), sb);
        sb.append("^_^");
        sb.append("ProtocalOptParams:");
        appendLog((BiexMap) rt.get("protocalOptParams"), sb);
        sb.append("^_^");
        sb.append("ApplicationParams:");
        appendLog((BiexMap) rt.get("textParams"), sb);
        sb.append("^_^");
        sb.append("Body:");
        sb.append((String) rt.get("rsp"));
        blog.error(sb.toString());
    }
    
    private static void appendLog(BiexMap map, StringBuilder sb)
    {
        boolean first = true;
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> entry : set)
        {
            if (!first)
            {
                sb.append("&");
            }
            else
            {
                first = false;
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
    }
    
    public static Boolean isBizDebugEnabled()
    {
        return blog.isDebugEnabled();
    }
    
    /**
     * Open the DEBUG level log (only for JDK14LOGGER, LOG4J please modify the configuration file yourself)
     * 
     * @param isEnabled
     */
    public static void setJDKDebugEnabled(Boolean isEnabled)
    {
        // 如果使用JDK14LOGGER，将业务日志级别设为DEBUG(FINE)
        if (blog instanceof Jdk14Logger)
        {
            Jdk14Logger logger = (Jdk14Logger) blog;
            if (isEnabled)
            {
                logger.getLogger().setLevel(Level.FINE);
                Handler consoleHandler = new ConsoleHandler();
                consoleHandler.setLevel(Level.FINE);
                logger.getLogger().addHandler(consoleHandler);
            }
            else
            {
                logger.getLogger().setLevel(Level.INFO);
            }
        }
    }
}
