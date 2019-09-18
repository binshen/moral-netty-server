package com.moral.util;

public class NetUtils {

    public static String bytesToHex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if(hex.length() < 2) {
                hex = "0" + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
