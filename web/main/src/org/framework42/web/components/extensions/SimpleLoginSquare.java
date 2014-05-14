package org.framework42.web.components.extensions;

import org.framework42.i18n.I18N;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.*;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.pages.WebPage;
import org.framework42.web.session.UserSession;

import java.util.Locale;

public class SimpleLoginSquare extends HtmlComponent {

    private Div data;

    /**
     * A simple implementation of a login.<br>
     * <br>
     * I18N to implement:<br>
     * login_title = The title of the login form.<br>
     * login_button = The title of the login button.<br>
     * */
    public SimpleLoginSquare(PageModel model, UserSession session) {

        Locale locale = session.getLocale();
        I18N i18n = I18N.INSTANCE;

        Div.Builder loginSquare = new Div.Builder("login_square");

        loginSquare.className("login_square");


        loginSquare.add(new Headline.Builder(Headline.H2, i18n.get("login_title", locale)).className("white").build());

        Form.Builder loginForm = new Form.Builder("login_form", i18n.getURL("login_page", locale), "login_form");

        loginForm.add(
                new Label(i18n.get("user_name", locale)),
                Break.I1,
                new TextField.Builder("user_name").autoFocus(true).build(),
                Break.I1,
                new Label(i18n.get("password", locale)),
                Break.I1,
                new PasswordField("password"),
                Break.I2,
                new SubmitButton.Builder("login_button", i18n.get("login_button", locale)).build()
        );

        loginSquare.add(loginForm.build());

        data = loginSquare.build();
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(data.getHtml(page, parent, false));
    }
}
