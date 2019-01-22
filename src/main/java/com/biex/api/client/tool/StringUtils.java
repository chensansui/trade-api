package com.biex.api.client.tool;

/**
 * String tool class.
 * 
 * @since 1.0, Sep 12, 2009
 */
public abstract class StringUtils
{
    private StringUtils()
    {
    }
    
    /**
     * Check if the specified string is empty.
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     * 
     * @param value String to be checked
     * @return true/false
     */
    public static boolean isEmpty(String value)
    {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) { return true; }
        for (int i = 0; i < strLen; i++)
        {
            if ((Character.isWhitespace(value.charAt(i)) == false)) { return false; }
        }
        return true;
    }
    
    /**
     * Checks if the object is a numeric string containing a negative number.
     */
    public static boolean isNumeric(Object obj)
    {
        if (obj == null) { return false; }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1) return false;
        int i = 0;
        if (length > 1 && chars[0] == '-') i = 1;
        for (; i < length; i++)
        {
            if (!Character.isDigit(chars[i])) { return false; }
        }
        return true;
    }
    
    /**
     * Checks if the specified string list is not empty.
     */
    public static boolean areNotEmpty(String ... values)
    {
        boolean result = true;
        if (values == null || values.length == 0)
        {
            result = false;
        }
        else
        {
            for (String value : values)
            {
                result &= !isEmpty(value);
            }
        }
        return result;
    }
    
    /**
     * Converts a universally encoded string into a Chinese character encoding.
     */
    public static String unicodeToChinese(String unicode)
    {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode))
        {
            for (int i = 0; i < unicode.length(); i++)
            {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }
    
    /**
     * Filter invisible characters
     */
    public static String stripNonValidXMLCharacters(String input)
    {
        if (input == null || ("".equals(input))) return "";
        StringBuilder out = new StringBuilder();
        char current;
        for (int i = 0; i < input.length(); i++)
        {
            current = input.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF)) || ((current >= 0xE000) && (current <= 0xFFFD))
                    || ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }
}
