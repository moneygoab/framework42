package org.framework42.annotationsinterface;


import org.framework42.model.service.ExecuteRunType;

/**
 * Created by ling on 2016-12-15.
 */
public interface ExecutorListener {

    void invokeMethod(int id, ExecutorObject object, ExecuteRunType type);

}
