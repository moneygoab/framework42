package org.framework42.examples.hello_authorization;

import org.apache.log4j.BasicConfigurator;
import org.framework42.authorization.UserAuthAction;
import org.framework42.authorization.UserAuthPerformer;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.model.users.Role;
import org.framework42.model.users.User;
import org.framework42.model.users.UserRole;
import org.framework42.model.users.RoleStatus;
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

        UserAuthPerformer authPerformer1 = createUserAuthPerformer(user1);

        UserAuthPerformer authPerformer2 = createUserAuthPerformer(user2);

        try {

            System.out.println("Authorizing user: " + user1);

            authPerformer1.authorize(UserAuthAction.HAS_VALID_ROLE);

            System.out.println("Authorized user: " + user1);
            System.out.println("Authorizing user: " + user2);

            authPerformer2.authorize(UserAuthAction.HAS_VALID_ROLE);

            System.out.println("Authorized user: " + user2);

        } catch (NotAuthorizedException e) {

            System.out.println(e);

        }

    }

    private User createUser1() {

        Map<Role, UserRole> userRoles = new HashMap<Role, UserRole>();
        userRoles.put(Role.MEMBER, new UserRoleImpl<Role, org.framework42.model.users.RoleStatus>(Role.MEMBER, RoleStatus.ACTIVE, new Date()));

        return new BaseUser(1, new Date(), "user@test.com", "Test User Member", userRoles);

    }

    private User createUser2() {

        Map<Role, UserRole> userRoles = new HashMap<Role, UserRole>();
        userRoles.put(Role.UNKNOWN_PERSON, new UserRoleImpl<Role, org.framework42.model.users.RoleStatus>(Role.UNKNOWN_PERSON, RoleStatus.ACTIVE, new Date()));

        return new BaseUser(2, new Date(), "visitor@test.com", "Test User Visitor", userRoles);

    }

    private UserAuthPerformer createUserAuthPerformer(User user) {

        List<Role> accessRoles = Arrays.asList(Role.ADMIN, Role.MEMBER);
        List<Role> denyRoles = Arrays.asList(Role.LOCKED);

        return new UserAuthPerformer(user, accessRoles, denyRoles);

    }

}
