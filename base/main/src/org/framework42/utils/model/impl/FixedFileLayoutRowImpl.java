package org.framework42.utils.model.impl;

import org.framework42.utils.model.FixedFileLayoutRow;

import java.util.List;

public class FixedFileLayoutRowImpl implements FixedFileLayoutRow {

    private final List<String> dataList;

    public FixedFileLayoutRowImpl(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public List<String> getDataList() {
        return dataList;
    }

    @Override
    public String getData() {

        StringBuilder sb = new StringBuilder();

        for(String data: dataList) {

            sb.append(data);
        }

        return sb.toString();
    }
}
