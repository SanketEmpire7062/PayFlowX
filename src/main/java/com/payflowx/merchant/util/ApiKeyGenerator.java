package com.payflowx.merchant.util;

import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateKey(){
        byte[] bytes = new byte[24];

        secureRandom.nextBytes(bytes);

        return "pfx_" + Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }
}
