package com.biex.api.client.bean.sign;

import com.biex.api.client.tool.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * A pure string dictionary structure.
 * 
 * @since 1.0, Sep 13, 2009
 */
public class BiexMap extends HashMap<String, String>
{
    private static final long serialVersionUID = -1277791390393392630L;
    
    public BiexMap()
    {
        super();
    }
    
    public BiexMap(Map<? extends String, ? extends String> m)
    {
        super(m);
    }
    
    public String put(String key, Object value)
    {
        String strValue;
        if (value == null)
        {
            strValue = null;
        }
        else if (value instanceof String)
        {
            strValue = (String) value;
        }
        else if (value instanceof Integer)
        {
            strValue = ((Integer) value).toString();
        }
        else if (value instanceof Long)
        {
            strValue = ((Long) value).toString();
        }
        else if (value instanceof Float)
        {
            strValue = ((Float) value).toString();
        }
        else if (value instanceof Double)
        {
            strValue = ((Double) value).toString();
        }
        else if (value instanceof Boolean)
        {
            strValue = ((Boolean) value).toString();
        }
        else
        {
            strValue = value.toString();
        }
        return this.put(key, strValue);
    }
    
    public String put(String key, String value)
    {
        if (StringUtils.areNotEmpty(key, value))
        {
            return super.put(key, value);
        }
        else
        {
            return null;
        }
    }
}
