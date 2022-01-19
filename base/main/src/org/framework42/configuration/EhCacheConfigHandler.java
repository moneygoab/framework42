package org.framework42.configuration;

import org.apache.log4j.Logger;

import net.sf.ehcache.CacheManager;

import javax.naming.ConfigurationException;

/**
 * Service class that handles loading the configuration for the cache.
 * */
public enum EhCacheConfigHandler {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.framework42");

    private EhCacheConfigHandler() {
    }

    /**
     * Loads the EhCache CacheManager
     * @return Returns the EhCache cache manager.
     * @throws ConfigurationException       If the configuration file isn't found in the filesystem this exception will be cast.
     * */
    public CacheManager load() throws ConfigurationException {

        String url = getClass().getClassLoader().getResource("").getPath();
        url = url.replaceAll("%20", " ");
        url += "resources" + System.getProperty("file.separator");
        CacheManager cacheManager;
        try{
            cacheManager = new CacheManager(url + "ehcache.xml");
        } catch(Exception e){

            try{
                cacheManager = new CacheManager("/tmp/ehcache.xml");
            } catch(Exception ex) { 
                String mess = "Could not load EhCache configuration, you must place the ehcache.xml file in [classpath]/resources";
                logger.fatal(mess);
                throw new ConfigurationException(mess);
            }
        }

        return cacheManager;

    }

}
