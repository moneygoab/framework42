package org.framework42.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum FileChecksum {

    INSTANCE;

    public String calculate(byte[] fileData) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(fileData);
            byte[] mdbytes = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {

            return "";
        }

    }

}
