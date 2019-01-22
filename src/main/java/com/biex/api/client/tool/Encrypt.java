/**
 * biex.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.biex.api.client.tool;

import com.biex.api.client.exception.ApiException;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 *  Encryption tool
 * 
 * @version $Id: Encrypt.java, v 0.1 2016-3-28 下午5:14:12 Exp $
 */
public class Encrypt
{
    private static final String   AES_ALG         = "AES";
    
    private static final String   HMAC_SHA1       = "HmacSHA1";
    
    private static final String   AES_CBC_PCK_ALG = "AES/CBC/PKCS5Padding";
    
    private final static String[] HEX_DIGITS      = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    
    /**
     *   encryption
     *
     * @param content
     * @param encryptType
     * @param encryptKey
     * @return {@link String}
     * @throws ApiException
     */
    public static String encryptContent(String content, String encryptType, String encryptKey) throws ApiException
    {
        if (AES_ALG.equals(encryptType))
        {
            String encryptContent;
            try
            {
                encryptContent = aesEncrypt(content, encryptKey);
            }
            catch (Exception e)
            {
                throw new ApiException(e.getLocalizedMessage());
            }
            return encryptContent;
        }
        else
        {
            throw new ApiException("This algorithm type is currently not supported：encrypeType=" + encryptType);
        }
    }
    
    /**
     *  Decrypt
     *
     * @param content
     * @param encryptType
     * @param encryptKey
     * @return {@link String}
     * @throws ApiException
     */
    public static String decryptContent(String content, String encryptType, String encryptKey) throws ApiException
    {
        if (AES_ALG.equals(encryptType))
        {
            String decryptContent;
            try
            {
                decryptContent = aesDecrypt(content, encryptKey);
            }
            catch (Exception e)
            {
                throw new ApiException(e.getLocalizedMessage());
            }
            return decryptContent;
        }
        else
        {
            throw new ApiException("This algorithm type is currently not supported：encrypeType=" + encryptType);
        }
    }

    /**
      * Encrypt using HmacSHA1
      *
      * @param data needs to encrypt data
      * @param key Encryption Key
      * @return
      */
    public static String hmacSha1(final String data, final String key) throws Exception
    {
        try
        {
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
            return byteArrayToHexString(mac.doFinal(data.getBytes()));
        }
        catch (NoSuchAlgorithmException | InvalidKeyException e)
        {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    /**
      * AES encryption is base 64 code
      * @param content content to be encrypted
      * @param encryptKey encryption key
      * @return Encrypted base 64 code
      * @throws Exception
      */
    public static String aesEncrypt(String content, String encryptKey) throws Exception
    {
        SecretKeySpec skeySpec = getKey(encryptKey);
        Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes("UTF-8"));
        return Base64.encodeStr(encrypted);
    }

    /**
      * Decrypt base 64 code AES
      * @param content base 64 code to be decrypted
      * @param decryptKey decryption key
      * @return decrypted string
      * @throws Exception
      */
    public static String aesDecrypt(String content, String decryptKey) throws Exception
    {
        SecretKeySpec skeySpec = getKey(decryptKey);
        Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.decode(content));
        String originalString = new String(original);
        return originalString;
    }
    
    private static SecretKeySpec getKey(String strKey)
    {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // Create an empty 16-bit byte array (default is 0)
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++)
        {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, AES_ALG);
        return skeySpec;
    }
    
    private static String byteArrayToHexString(byte[] b)
    {
        StringBuilder sb = new StringBuilder();
        for (byte aB : b)
        {
            sb.append(byteToHexString(aB));
        }
        return sb.toString();
    }
    
    private static String byteToHexString(byte b)
    {
        return HEX_DIGITS[(b & 0xf0) >> 4] + HEX_DIGITS[b & 0x0f];
    }
}
