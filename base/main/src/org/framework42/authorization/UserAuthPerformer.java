package org.framework42.authorization;

import org.apache.log4j.Logger;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.model.users.Role;
import org.framework42.model.users.User;

import java.util.List;

public class UserAuthPerformer extends AbstractAuthorizationPerformer {

    private final User user;

    protected final List<Role> accessRoles;
    protected final List<Role> denyAccessRoles;

    private final Logger logger = Logger.getLogger("org.framework42");

    public UserAuthPerformer(User user, List<Role> accessRoles, List<Role> denyAccessRoles) {

        this.user = user;

        this.accessRoles = accessRoles;
        this.denyAccessRoles = denyAccessRoles;

    }

    @Override
    protected void performAuthorization(AuthorizationAction authorizationAction, String resource) throws NotAuthorizedException {

        isUserDeniedAccess(user, resource);

        isUserGrantedAccess(user, resource);

    }

    private void isUserDeniedAccess(User user, String resource) throws NotAuthorizedException {

        for (Role role : denyAccessRoles) {

            if (user.getUserRoles().containsKey(role)) {

                logger.info("User getId: " + user.getId() + " displayName: " + user.getDisplayName() + " denied access on role: " + role+" to: "+resource);
                throw new NotAuthorizedException();
            }
        }
    }

    private void isUserGrantedAccess(User user, String resource) throws NotAuthorizedException {

        boolean accessGranted = false;

        if (accessRoles.size() == 0) {

            accessGranted = true;

        } else {

            for (Role role : accessRoles) {

                for (Role userRole: user.getUserRoles().keySet()) {
                    if(role.getId() == userRole.getId()) {
                        accessGranted = true;
                        break;
                    }
                }
            }
        }

        if (!accessGranted) {

            logger.info("User getId: " + user.getId() + " displayName: " + user.getDisplayName() + " not granted access to: "+resource+", has not any of roles: " + accessRoles);
            throw new NotAuthorizedException();
        }

    }

}
