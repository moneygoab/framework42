package org.framework42.http.server.html.body;

import org.framework42.http.server.html.HtmlComponentImpl;

public class BR extends HtmlComponentImpl {

    public final static BR I1 = new BR();
    public final static BR I2 = new BR(2);
    public final static BR I3 = new BR(3);
    public final static BR I4 = new BR(4);
    public final static BR I5 = new BR(5);
    public final static BR I6 = new BR(6);
    public final static BR I7 = new BR(7);
    public final static BR I8 = new BR(8);
    public final static BR I9 = new BR(9);
    public final static BR I10 = new BR(10);

    private final int numberOfBreaks;

    public BR() {

        numberOfBreaks = 1;
    }

    public BR(int numberOfBreaks) {

        this.numberOfBreaks = numberOfBreaks;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        for(int i=0;i<numberOfBreaks;i++) {

            htmlBuilder.append("<br>");
        }

        htmlBuilder.append("\n");
    }
}
