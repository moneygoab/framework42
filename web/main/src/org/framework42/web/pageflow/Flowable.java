package org.framework42.web.pageflow;

import org.framework42.web.pagemodel.PageAction;

import java.util.List;

public interface Flowable {

    public List<Flowable> getOriginatingPages(PageAction action);

}
