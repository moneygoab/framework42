package org.framework42.annotations;

import org.framework42.authorization.AuthType;
import org.framework42.model.users.impl.BaseRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Authorization {

    AuthType authType() default AuthType.USER_AUTH;

    int[] accessRoles() default {BaseRole.ADMIN.getId()};
    int[] denyRoles() default {
            BaseRole.DISMISSED.getId(),
            BaseRole.LOCKED.getId(),
            BaseRole.MUST_CHANGE_PASSWORD.getId(),
            BaseRole.UNKNOWN_PERSON.getId()
    };

}
