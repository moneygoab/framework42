package org.framework42.annotations;

import org.framework42.model.service.ExecuteRunType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ling on 2016-12-13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Executor {
    public int id();
    public ExecuteRunType runType();
    public boolean parameters() default true;
}