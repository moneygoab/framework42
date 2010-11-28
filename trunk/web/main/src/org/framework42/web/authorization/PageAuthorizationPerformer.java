package org.framework42.web.authorization;

import org.framework42.authorization.AbstractAuthorizationPerformer;
import org.framework42.authorization.AuthorizationAction;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.model.Role;
import org.framework42.model.users.User;

import java.util.List;

public class PageAuthorizationPerformer extends AbstractAuthorizationPerformer {

    private final User user;

    protected final List<Role> accessRoles;
    protected final List<Role> denyAccessRoles;

    public PageAuthorizationPerformer(User user, List<Role> accessRoles, List<Role> denyAccessRoles) {

        this.user = user;

        this.accessRoles = accessRoles;
        this.denyAccessRoles = denyAccessRoles;

    }

    @Override
    protected void performAuthorization(AuthorizationAction authorizationAction) throws NotAuthorizedException {

        isUserDeniedAccess(user);

        isUserGrantedAccess(user);

    }

    private void isUserDeniedAccess(User user) throws NotAuthorizedException {

        for (Role role : denyAccessRoles) {

            if (user.getUserRoles().containsKey(role)) {
                throw new NotAuthorizedException();
            }

        }

    }

    private void isUserGrantedAccess(User user) throws NotAuthorizedException {

        boolean accessGranted = false;

        if (accessRoles.size() == 0) {

            accessGranted = true;

        } else {

            for (Role role : accessRoles) {

                if (user.getUserRoles().containsKey(role)) {
                    accessGranted = true;
                    break;
                }
            }
        }

        if (!accessGranted) {
            throw new NotAuthorizedException();
        }

    }

}
