package com.zxy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//utility class
public class MD5Utils {

    /**
     * MD5 encoder
     * @param str ready to be encoded password
     * @return    password after encoded
     */
    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");

            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32 bit
            return buf.toString();
            // 16 bit
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    //use the main method to get encoded password
    public static void main(String[] args) {
        System.out.println(code("111111"));
    }
}
