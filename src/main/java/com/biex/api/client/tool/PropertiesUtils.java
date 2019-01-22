/*
 * @(#)Dialect.java 2015-4-17 下午3:29:26
 * Copyright 2015 Playguy, Inc. All rights reserved. com.biex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.biex.api.client.tool;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * <p>File：PropertiesUtils.java</p>
 * <p>Title: PropertiesUtils</p>
 * <p>Description: Properties File Tool</p>
 * <p>Copyright: Copyright (c) 2015/04/18 11:29</p>
 *
 * @version 1.0
 */
public class PropertiesUtils
{
    private static Logger         logger         = LoggerFactory.getLogger(PropertiesUtils.class);

    private final Properties      properties;

    public PropertiesUtils(String ... resourcesPaths)
    {
        properties = loadProperties(resourcesPaths);
    }

    public Properties getProperties()
    {
        return properties;
    }

    /**
     * Take out the Property, but take the Property property of the System first, and you can't get the empty string.
     */
    private String getValue(String key)
    {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) { return systemProperty; }
        if (properties.containsKey(key)) { return properties.getProperty(key); }
        return "";
    }

    /**
     * Take a Property of type String, but take the Property of System first, and throw an exception if it is all Null.
     */
    public String getProperty(String key)
    {
        String value = getValue(key);
        if (value == null) { throw new NoSuchElementException(); }
        return value;
    }

    /**
     * Take the Property of the String type, but take the Property of the System first. If it is Null, return the Default value.
     */
    public String getProperty(String key, String defaultValue)
    {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Take out the Property of type Integer, but take the Property of System first. If all are Null or the content is wrong, an exception is thrown.
     */
    public Integer getInteger(String key)
    {
        String value = getValue(key);
        if (value == null) { throw new NoSuchElementException(); }
        return Integer.valueOf(value);
    }

    /**
     * Take the Property of type Integer, but take the Property of System first. If it is Null, it will return the Default value. If the content is wrong, an exception will be thrown.
     */
    public Integer getInteger(String key, Integer defaultValue)
    {
        String value = getValue(key);
        return StringUtils.isNotBlank(value) ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * Take the Property of the Double type, but take the Property of the System first. If all are Null or the content is wrong, an exception is thrown.
     */
    public Double getDouble(String key)
    {
        String value = getValue(key);
        if (value == null) { throw new NoSuchElementException(); }
        return Double.valueOf(value);
    }

    /**
     * Take the Property of the Double type, but take the Property of the System first. If all are Null or the content is wrong, an exception is thrown.
     */
    public Long getLong(String key)
    {
        String value = getValue(key);
        if (value == null) { throw new NoSuchElementException(); }
        return Long.valueOf(value);
    }

    /**
     * Take the Property of the Double type, but take the Property of the System first. If all are Null or the content is wrong, an exception is thrown.
     */
    public Long getLong(String key, long defaultValue)
    {
        String value = getValue(key);
        return value != null ? Long.valueOf(value) : defaultValue;
    }

    /**
     * Take the Property of the Double type, but take the Property of the System first. If it is Null, it will return the Default value. If the content is wrong, an exception will be thrown.
     */
    public Double getDouble(String key, Integer defaultValue)
    {
        String value = getValue(key);
        return value != null ? Double.valueOf(value) : defaultValue;
    }

    /**
     * Take out the Boolean type property, but take the System Property first. If both throw an exception for Null, return false if the content is not true/false.
     */
    public Boolean getBoolean(String key)
    {
        String value = getValue(key);
        if (value == null) { throw new NoSuchElementException(); }
        return Boolean.valueOf(value);
    }

    /**
     * Take out the Boolean type property, but take the System Property first. If it is Null, it returns the Default value. If the content is not true/false, it returns false.
     */
    public Boolean getBoolean(String key, boolean defaultValue)
    {
        String value = getValue(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }

    /**
     * Load multiple files, the file path uses the Spring Resource format.
     */
    private Properties loadProperties(String ... resourcesPaths)
    {
        Properties props = new Properties();
        for (String location : resourcesPaths)
        {
            InputStream is = null;
            try
            {
                is = PropertiesUtils.class.getClassLoader().getResourceAsStream(location);
                props.load(is);
                is.close();
            }
            catch (IOException ex)
            {
                logger.info("Could not load properties from path:" + location + ", " + ex.getMessage());
            }
        }
        return props;
    }
}
