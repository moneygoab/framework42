package org.framework42.utils.services.impl;

import org.framework42.utils.model.FixedFileLayoutRow;
import org.framework42.utils.model.impl.FixedFileLayoutRowImpl;
import org.framework42.utils.services.FixedFileLayout;

import java.util.ArrayList;
import java.util.List;

public class FixedFileLayoutImpl implements FixedFileLayout {

    private final List<FixedFileLayoutRow> rowList;

    public FixedFileLayoutImpl(List<FixedFileLayoutRow> rowList) {

        this.rowList = rowList;
    }

    @Override
    public List<FixedFileLayoutRow> getRowList() {
        return rowList;
    }

    @Override
    public String getDataString() {

        StringBuilder sb = new StringBuilder();

        for(FixedFileLayoutRow row: rowList) {

            sb.append(row.getData());
            sb.append("\n");
        }

        return sb.toString();
    }

    public static class Builder {

        private List<FixedFileLayoutRow> rowList;

        public Builder() {

            rowList = new ArrayList<FixedFileLayoutRow>();
        }

        public void addRow(FixedFileLayoutRow... rows) {

            for(FixedFileLayoutRow row: rows) {

                rowList.add(row);
            }
        }

        public void addRow(String... data) {

            List<String> dataList = new ArrayList<String>();

            for(String d: data) {

                dataList.add(d);
            }

            rowList.add(new FixedFileLayoutRowImpl(dataList));
        }

        public FixedFileLayout build() {

            return new FixedFileLayoutImpl(rowList);
        }

    }


}
