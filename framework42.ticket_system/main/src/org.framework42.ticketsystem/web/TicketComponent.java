package org.framework42.ticketsystem.web;

import org.framework42.i18n.I18N;
import org.framework42.ticketsystem.model.Ticket;
import org.framework42.web.components.ComponentGroup;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Break;
import org.framework42.web.components.standardhtml.Headline;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.pages.WebPage;
import org.framework42.web.session.UserSession;

import java.util.Locale;

public class TicketComponent extends HtmlComponent {

    private ComponentGroup components;

    public TicketComponent(UserSession session, Ticket ticket) {

        I18N i18n = I18N.INSTANCE;
        Locale locale = session.getLocale();

        ComponentGroup.Builder builder = new ComponentGroup.Builder();

        builder.add(new Headline(Headline.H2, ticket.getTitle()));

        builder.add(new Label("Milstolpe: "));
        builder.add(new Label(ticket.getMilestone().getTitle()));
        builder.add(new Break());

        components = builder.build();
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(components.getHtml(page, parent));
    }
}
