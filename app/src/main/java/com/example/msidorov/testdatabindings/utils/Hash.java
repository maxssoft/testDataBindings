package com.example.msidorov.testdatabindings.utils;

/**
 * @author m.sidorov
 */
public class Hash {

    private static String getHash(String value, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(value.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5(String value){
        return getHash(value, "MD5");
    }

    public static String sha1(String value){
        return getHash(value, "SHA1");
    }
}
