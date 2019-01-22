package com.biex.api.client.tool;

import com.biex.api.client.bean.sign.BiexMap;
import com.biex.api.client.bean.sign.RequestHolder;
import com.biex.api.client.constant.BiexConstants;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * HmacSHA1Signature Introduce
 * <p>File：HmacSHA1Signature.java</p>
 * <p>Title: HmacSHA1Signature</p>
 * <p>Description: HmacSHA1Signature</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class HmacSHA256Signature {

    /* Signature method. */
    private static final String ALGORITHM_SHA256 = BiexConstants.SIGN_TYPE_SHA256;

    /**
     * Sign the given message using the given secret.
     * HmacSHA256签名方法
     *
     * @param message message to sign
     * @param secret  secret key
     * @return a signed message
     */
    public static String sign_256(String message, String secret, String charset) {
        try {
            Mac sha256_HMAC = Mac.getInstance(ALGORITHM_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(StringUtils.isEmpty(charset) ? secret.getBytes() : secret.getBytes(charset), ALGORITHM_SHA256);
            sha256_HMAC.init(secretKeySpec);
            return Base64.encodeStr(sha256_HMAC.doFinal(StringUtils.isEmpty(charset) ? message.getBytes() : message.getBytes(charset)));
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to sign message.", e);
        }
    }

    /**
     * Convert the request object to text
     *
     * @param requestHolder
     * @return
     */
    public static String getSignatureContent(RequestHolder requestHolder) {
        return getSignContent(getSortedMap(requestHolder));
    }

    /**
     * Convert a Map parameter object to text
     *
     * @param sortedParams
     * @return
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtils.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * Collection deduplication and sorting
     *
     * @param requestHolder
     * @return
     */
    public static Map<String, String> getSortedMap(RequestHolder requestHolder) {
        Map<String, String> sortedParams = new TreeMap<String, String>();
        BiexMap appParams = requestHolder.getApplicationParams();
        if (appParams != null && appParams.size() > 0) {
            sortedParams.putAll(appParams);
        }
        BiexMap protocalMustParams = requestHolder.getProtocalMustParams();
        if (protocalMustParams != null && protocalMustParams.size() > 0) {
            sortedParams.putAll(protocalMustParams);
        }
        BiexMap protocalOptParams = requestHolder.getProtocalOptParams();
        if (protocalOptParams != null && protocalOptParams.size() > 0) {
            sortedParams.putAll(protocalOptParams);
        }
        return sortedParams;
    }

}
