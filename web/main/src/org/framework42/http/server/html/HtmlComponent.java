package org.framework42.http.server.html;

public interface HtmlComponent {

    public String getHtml(HtmlComponentImpl parent);

    public String getHtml(HtmlComponentImpl parent, boolean onSameRow);

}
