package com.itluobo.wechat.uitls;

import com.itluobo.wechat.mvc.entity.AccessDeniedException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AuthUtils {
    /**
     * Sign request based on SHA-1.
     * @param param1 first param.
     * @param param2 second param.
     * @param param3 third param.
     * @return signed string.
     */
    public static String sign(String param1, String param2, String param3) {
        List<String> params = Arrays.asList(param1, param2, param3);
        Collections.sort(params);

        String paramStr = params.get(0) + params.get(1) + params.get(2);

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(paramStr.getBytes());
            String result = byte2hex(messageDigest.digest());
            return result;
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Error sign params",e );
        }
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }
}
