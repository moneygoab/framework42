package org.framework42.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class SimpleAES {

    private final static String simpleKey = "MoneygoSimpleKey";

    public static SecretKeySpec getKey(String myKey) {

        MessageDigest sha = null;

        byte[] key;

        try {

            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);

            return new SecretKeySpec(key, "AES");

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        return null;
    }



    public static String simpleEncrypt(String encrypt){

        return URLEncoder.encode(encrypt(encrypt,simpleKey));
    }

    public static String encrypt(String encrypt, String secret) {

        try {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey(secret));

            return Base64.getEncoder().encodeToString(cipher.doFinal(encrypt.getBytes("UTF-8")));

        } catch (Exception e) {

            System.out.println("Error while encrypting: " + e.toString());
        }

        return null;
    }

    public static String simpleDecrypt(String decrypt){

        return URLDecoder.decode(decrypt(decrypt, simpleKey));
    }

    public static String decrypt(String decrypt, String secret) {

        try {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, getKey(secret));

            return new String(cipher.doFinal(Base64.getDecoder().decode(decrypt)));

        } catch (Exception e) {

            System.out.println("Error while decrypting: " + e.toString());
        }

        return null;
    }

}
