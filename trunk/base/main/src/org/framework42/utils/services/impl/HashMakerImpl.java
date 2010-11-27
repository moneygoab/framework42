package org.framework42.utils.services.impl;

import org.framework42.utils.services.HashMaker;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum HashMakerImpl implements HashMaker {

   INSTANCE;

   Logger logger = Logger.getLogger("com.nummer42.poeter");

   private HashMakerImpl() {
   }

   public String getHash(String salt, String stringToHash) throws NullPointerException{

      if(salt==null||stringToHash==null){
         throw new NullPointerException();
      }

      String passHash = "";

      try{

         byte[] theTextToDigestAsBytes = (salt+stringToHash).getBytes("UTF-8");
         MessageDigest md = MessageDigest.getInstance("SHA-256");
         md.update( theTextToDigestAsBytes );
         byte[] digest = md.digest();

         for ( byte b : digest ){
            if(Integer.toHexString( b & 0xff ).length()==1){
               passHash += "0"+Integer.toHexString( b & 0xff );
            }else{
               passHash += Integer.toHexString( b & 0xff );
            }

         }

      }catch(UnsupportedEncodingException e){
         logger.fatal("HashMaker.getHash: "+e);
         throw new RuntimeException();
      }catch(NoSuchAlgorithmException e){
         logger.fatal("HashMaker.getHash: "+e);
         throw new RuntimeException();
      }

      return passHash;

   }

}
