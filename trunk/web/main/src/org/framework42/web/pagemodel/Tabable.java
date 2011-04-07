package org.framework42.web.pagemodel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Tabable {

    TabCacheType tabCacheType() default TabCacheType.CACHE_ON_LEAVING_TAB;

}
