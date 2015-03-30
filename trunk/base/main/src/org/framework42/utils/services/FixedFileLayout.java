package org.framework42.utils.services;

import org.framework42.utils.model.FixedFileLayoutRow;

import java.util.List;

public interface FixedFileLayout {

    public List<FixedFileLayoutRow> getRowList();

    public String getDataString();

}
