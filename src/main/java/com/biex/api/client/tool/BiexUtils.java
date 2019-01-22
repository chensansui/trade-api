package com.biex.api.client.tool;

import com.biex.api.client.bean.sign.BiexResponse;
import com.biex.api.client.bean.sign.JSONReader;
import com.biex.api.client.bean.sign.JSONValidatingReader;
import com.biex.api.client.bean.sign.ObjectJsonParser;
import com.biex.api.client.exception.ApiException;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * System tool class.
 * 
 * @since 1.0, Sep 12, 2009
 */
public abstract class BiexUtils
{
    private static String localIp;
    
    private BiexUtils()
    {
    }
    
    /**
     * Get the real suffix of the file. Currently only supports JPG, GIF, PNG, BMP four image files.
     * 
     * @param bytes File byte stream
     * @return JPG, GIF, PNG or null
     */
    public static String getFileSuffix(byte[] bytes)
    {
        if (bytes == null || bytes.length < 10) { return null; }
        if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F')
        {
            return "GIF";
        }
        else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G')
        {
            return "PNG";
        }
        else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F')
        {
            return "JPG";
        }
        else if (bytes[0] == 'B' && bytes[1] == 'M')
        {
            return "BMP";
        }
        else
        {
            return null;
        }
    }

    /**
      * Get the real media type of the file. Currently only supports JPG, GIF, PNG, BMP four image files.
      *
      * @param bytes file byte stream
      * @return Media Type (MEME-TYPE)
      */
    public static String getMimeType(byte[] bytes)
    {
        String suffix = getFileSuffix(bytes);
        String mimeType;
        if ("JPG".equals(suffix))
        {
            mimeType = "image/jpeg";
        }
        else if ("GIF".equals(suffix))
        {
            mimeType = "image/gif";
        }
        else if ("PNG".equals(suffix))
        {
            mimeType = "image/png";
        }
        else if ("BMP".equals(suffix))
        {
            mimeType = "image/bmp";
        }
        else
        {
            mimeType = "application/octet-stream";
        }
        return mimeType;
    }

    /**
      * Clear items with empty values in the dictionary.
      *
      * @param <V> Generics
      * @param map dictionary to be cleared
      * @return Cleared dictionary
      */
    public static <V> Map<String, V> cleanupMap(Map<String, V> map)
    {
        if (map == null || map.isEmpty()) { return null; }
        Map<String, V> result = new HashMap<String, V>(map.size());
        Set<Entry<String, V>> entries = map.entrySet();
        for (Entry<String, V> entry : entries)
        {
            if (entry.getValue() != null)
            {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    /**
      * Convert JSON strings to Map structures.
      *
      * @param body JSON string
      * @return Map structure
      */
    public static Map<?, ?> parseJson(String body)
    {
        JSONReader jr = new JSONValidatingReader();
        Object obj = jr.read(body);
        if (obj instanceof Map<?, ?>)
        {
            return (Map<?, ?>) obj;
        }
        else
        {
            return null;
        }
    }

    /**
      * Interpret JSON strings as object structures.
      *
      * @param <T> API response type
      * @param json JSON string
      * @param clazz API response class
      * @return API response object
      */
    public static <T extends BiexResponse> T parseResponse(String json, Class<T> clazz) throws ApiException
    {
        ObjectJsonParser<T> parser = new ObjectJsonParser<T>(clazz);
        return parser.parse(json);
    }
    
    /**
     * Get the network IP of this machine
     */
    public static String getLocalNetWorkIp()
    {
        if (localIp != null) { return localIp; }
        try
        {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements())
            {// Traverse all network cards
                NetworkInterface ni = netInterfaces.nextElement();
                if (ni.isLoopback() || ni.isVirtual())
                {// If it is a loopback and virtual network address, continue
                    continue;
                }
                Enumeration<InetAddress> addresss = ni.getInetAddresses();
                while (addresss.hasMoreElements())
                {
                    InetAddress address = addresss.nextElement();
                    if (address instanceof Inet4Address)
                    {// Here only temporarily get the ipv4 address
                        ip = address;
                        break;
                    }
                }
                if (ip != null)
                {
                    break;
                }
            }
            if (ip != null)
            {
                localIp = ip.getHostAddress();
            }
            else
            {
                localIp = "127.0.0.1";
            }
        }
        catch (Exception e)
        {
            localIp = "127.0.0.1";
        }
        return localIp;
    }
}
