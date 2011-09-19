package org.framework42.annotations;

import org.framework42.authorization.AuthType;
import org.framework42.model.users.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Authorization {

    AuthType authType() default AuthType.USER_AUTH;

    Role[] accessRoles() default {Role.ADMIN};
    Role[] denyRoles() default {
            Role.DISMISSED,
            Role.LOCKED,
            Role.MUST_CHANGE_PASSWORD
    };

}
