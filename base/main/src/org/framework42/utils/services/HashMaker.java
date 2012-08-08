package org.framework42.utils.services;

/**
 * Utility class for making a hash using a strong method.
 */
public interface HashMaker {

    /**
     * Returns a hash value of the sent in salt and string to hash.
     *
     * @param salt         A salt that may be used to make it harder to revers the hash.
     * @param stringToHash The actual text to make a hash of.
     * @return String       The hashed value of the salt and string to hash.
     * @throws IllegalArgumentException is thrown if the salt or stringToHash contains an illegal null value,
     */
    public String getHash(String salt, String stringToHash) throws IllegalArgumentException;

    /**
     * Returns a hash value of the sent in salt and string to hash.
     *
     * @param salt         A salt that may be used to make it harder to revers the hash.
     * @param stringToHash The actual text to make a hash of.
     * @param digestType   The type of digest, ie. SHA-256 or MD5.
     * @return String       The hashed value of the salt and string to hash.
     * @throws IllegalArgumentException is thrown if the salt or stringToHash contains an illegal null value,
     */
    public String getHash(String salt, String stringToHash, String digestType) throws IllegalArgumentException;

}
