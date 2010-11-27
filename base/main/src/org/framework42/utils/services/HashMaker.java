package org.framework42.utils.services;

/**
 * Utility class for making a hash using a strong method.
 * */
public interface HashMaker {

    /**
     * Returns a hash value of the sent in salt and string to hash.
     * @param salt          A salt that may be used to make it harder to revers the hash.
     * @param stringToHash  The acctual text to make a hash of.
     *
     * @return String       The hashed value.
     * @throws NullPointerException is thrown if the salt or stringToHash contains an illegal null value,
     *
     * */
    public String getHash(String salt,String stringToHash) throws NullPointerException;

}
