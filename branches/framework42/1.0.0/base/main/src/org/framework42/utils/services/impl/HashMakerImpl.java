package org.framework42.utils.services.impl;

import org.apache.log4j.Logger;
import org.framework42.utils.services.HashMaker;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.framework42.utils.NullChecker.notNull;

public enum HashMakerImpl implements HashMaker {

    INSTANCE;

    Logger logger = Logger.getLogger("org.framework42.utils");

    private HashMakerImpl() {
    }

    /**
     * Call this method to generate a hash
     * @param salt              A salt to use to make it harder to reverse engineer the hash to the password. Send in an empty string if you don't want to use any salt.
     * @param stringToHash      The actual string / password to hash.
     * @return The hashed string.
     * */
    public String getHash(String salt, String stringToHash) throws IllegalArgumentException {

        notNull(salt, "Salt can't be null!");
        notNull(stringToHash, "String to hash can't be null!");

        StringBuilder passBuilder = new StringBuilder();

        try {

            byte[] theTextToDigestAsBytes = (salt + stringToHash).getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(theTextToDigestAsBytes);
            byte[] digest = md.digest();

            for (byte b : digest) {
                if (Integer.toHexString(b & 0xff).length() == 1) {
                    passBuilder.append("0");
                    passBuilder.append(Integer.toHexString(b & 0xff));
                } else {
                    passBuilder.append(Integer.toHexString(b & 0xff));
                }

            }

        } catch (UnsupportedEncodingException e) {

            logger.fatal("HashMakerImpl.getHash: " + e);
            throw new RuntimeException("HashMakerImpl.getHash: " + e);

        } catch (NoSuchAlgorithmException e) {

            logger.fatal("HashMakerImpl.getHash: " + e);
            throw new RuntimeException("HashMakerImpl.getHash: " + e);
        }

        return passBuilder.toString();

    }

}
