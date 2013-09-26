package org.framework42.ticketsystem.web;

import org.framework42.i18n.I18N;
import org.framework42.ticketsystem.model.Milestone;
import org.framework42.web.components.ComponentGroup;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.*;
import org.framework42.web.pages.WebPage;
import org.framework42.web.session.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddTicketForm extends HtmlComponent {

    private ComponentGroup components;

    public AddTicketForm(UserSession session, List<Milestone> milestonesList) {

        I18N i18n = I18N.INSTANCE;
        Locale locale = session.getLocale();

        ComponentGroup.Builder builder = new ComponentGroup.Builder();

        builder.add(new Headline(Headline.H3, "Lägg till att-göra-biljett"));

        Form.Builder form = new Form.Builder("add_ticket_form", i18n.getURL("main_view_tickets", locale), "add_ticket");

        form.add(new FormLabel("Titel", "title_field"));
        form.add(new TextField("title_field"));
        form.add(new Break());
        form.add(new FormLabel("Prioritet", "priority_field"));
        form.add(new TextField("priority_field"));
        form.add(new Break());
        form.add(new FormLabel("Milstolpe", "milestone_field"));
        List<Option> optionList = new ArrayList<Option>();
        for(Milestone milestone: milestonesList) {
            optionList.add(new Option(milestone.getId(), milestone.getTitle()));
        }
        form.add(new ComboBox("milestone_combo_box", "milestone_combo_box", optionList));
        form.add(new Break());
        form.add(new FormLabel("Beskrivning", "description_area"));
        form.add(new Break());
        form.add(new TextArea("description_area"));

        form.add(new FormLabel("&nbsp;", "submit_button"));
        form.add(new SubmitButton("submit_button", "Lägg till att-göra-biljett"));

        builder.add(form.build());

        components = builder.build();
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(components.getHtml(page, parent));
    }
}
