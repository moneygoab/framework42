package org.framework42.utils.services;

import org.framework42.utils.services.impl.HashMakerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HashMakerTester {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testHashMaker() throws Exception {

        HashMaker hashMaker = HashMakerImpl.INSTANCE;

        String hashedString = hashMaker.getHash("test", "stringToHash");

        if (!hashedString.equals("b6133745309c4c5390e055632977468352376ade37af7f1195858623cdd2e375")) {

            throw new Exception("");

        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHashMakerIllegalArguments() throws Exception {

        HashMaker hashMaker = HashMakerImpl.INSTANCE;

        hashMaker.getHash(null, null);

    }

}
