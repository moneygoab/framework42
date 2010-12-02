package org.framework42.examples.hello_authorization;

import org.apache.log4j.BasicConfigurator;
import org.framework42.authorization.UserAuthorizationAction;
import org.framework42.authorization.UserAuthorizationPerformer;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.model.users.Role;
import org.framework42.model.users.RoleStatus;
import org.framework42.model.users.User;
import org.framework42.model.users.UserRole;
import org.framework42.model.users.impl.BaseRole;
import org.framework42.model.users.impl.BaseRoleStatus;
import org.framework42.model.users.impl.BaseUser;
import org.framework42.model.users.impl.UserRoleImpl;

import java.util.*;

public class HelloAuthorization {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        new HelloAuthorization();

    }

    public HelloAuthorization() {

        User user1 = createUser1();
        User user2 = createUser2();

        UserAuthorizationPerformer authPerformer1 = createUserAuthorizationPerformer(user1);

        UserAuthorizationPerformer authPerformer2 = createUserAuthorizationPerformer(user2);

        try {

            System.out.println("Authorizing user: " + user1);

            authPerformer1.authorize(UserAuthorizationAction.HAS_VALID_ROLE);

            System.out.println("Authorized user: " + user1);
            System.out.println("Authorizing user: " + user2);

            authPerformer2.authorize(UserAuthorizationAction.HAS_VALID_ROLE);

            System.out.println("Authorized user: " + user2);

        } catch (NotAuthorizedException e) {

            System.out.println(e);

        }

    }

    private User createUser1() {

        Map<Role, UserRole> userRoles = new HashMap<Role, UserRole>();
        userRoles.put(BaseRole.MEMBER, new UserRoleImpl<Role, RoleStatus>(BaseRole.MEMBER, BaseRoleStatus.ACTIVE));

        return new BaseUser(1, Calendar.getInstance().getTime(), "user@test.com", "Test User Member", userRoles);

    }

    private User createUser2() {

        Map<Role, UserRole> userRoles = new HashMap<Role, UserRole>();
        userRoles.put(BaseRole.VISITOR, new UserRoleImpl<Role, RoleStatus>(BaseRole.VISITOR, BaseRoleStatus.ACTIVE));

        return new BaseUser(2, Calendar.getInstance().getTime(), "visitor@test.com", "Test User Visitor", userRoles);

    }

    private UserAuthorizationPerformer createUserAuthorizationPerformer(User user) {

        List<Role> accessRoles = Arrays.asList((Role) BaseRole.ADMIN, BaseRole.MEMBER);
        List<Role> denyRoles = Arrays.asList((Role) BaseRole.LOCKED);

        return new UserAuthorizationPerformer(user, accessRoles, denyRoles);

    }

}
