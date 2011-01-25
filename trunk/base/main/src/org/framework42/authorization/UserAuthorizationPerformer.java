package org.framework42.authorization;

import org.apache.log4j.Logger;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.model.users.Role;
import org.framework42.model.users.User;

import java.util.List;

public class UserAuthorizationPerformer extends AbstractAuthorizationPerformer {

    private final User user;

    protected final List<Role> accessRoles;
    protected final List<Role> denyAccessRoles;

    private final Logger logger = Logger.getLogger("org.framework42");

    public UserAuthorizationPerformer(User user, List<Role> accessRoles, List<Role> denyAccessRoles) {

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
                logger.debug("User getId: " + user.getUserID() + " displayName: " + user.getDisplayName() + " denied access on role: " + role);
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
            logger.debug("User getId: " + user.getUserID() + " displayName: " + user.getDisplayName() + " not granted access, has not any of roles: " + accessRoles);
            throw new NotAuthorizedException();
        }

    }

}
