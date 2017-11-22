package org.framework42.annotationsinterface;


import org.framework42.model.service.ExecuteRunType;
import org.framework42.model.users.User;

import java.lang.reflect.Method;

/**
 * Created by ling on 2016-12-15.
 */
public interface ExecutorListener {

    void invokeMethod(int id, ExecutorObject object, ExecuteRunType type, User user, String uniqueMethodId, Method method);

}
