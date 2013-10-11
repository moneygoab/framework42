package org.framework42.web.plugins.TinyMCEImageHandler;

import org.framework42.exceptions.ManageableException;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.exceptions.UserBlockedException;
import org.framework42.model.MimeType;
import org.framework42.model.users.Role;
import org.framework42.web.components.ComponentGroup;
import org.framework42.web.components.EncodingType;
import org.framework42.web.components.js_components.JavaScript;
import org.framework42.web.components.standardhtml.*;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pages.WebPage;
import org.framework42.web.session.UserSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class TinyMCEImageHandlerPage extends WebPage<UserSession, TinyMCEImageHandlerPageModel> {

    public TinyMCEImageHandlerPage() {
        super(
                "org.framework42.web.plugin.tinymce_image_handler",
                new TinyMCEImageHandlerPageLogic(),
                Arrays.asList(Role.MEMBER, Role.VIP_MEMBER),
                Arrays.asList(Role.UNKNOWN_PERSON, Role.DISMISSED, Role.LOCKED)
        );
    }

    @Override
    protected void doGetSub(TinyMCEImageHandlerPageModel model, UserSession session, Html.Builder htmlBuilder) throws ServletException, IOException {

        ComponentGroup.Builder builder = new ComponentGroup.Builder();

        if(model.isImageUploaded()) {

            JavaScript.Builder js = new JavaScript.Builder();

            js.addScriptLine("var win = (!window.frameElement && window.dialogArguments) || opener || parent || top;");
            js.addScriptLine("win.tinymce.EditorManager.activeEditor.insertContent('<img src=\""+model.getFileUrl()+"\" class=\"blog_image\">');");
            js.addScriptLine("win.tinymce.EditorManager.activeEditor.windowManager.close(window);");
            builder.add(js.build());
        }

        builder.add(new Span.Builder(new Label("Välj en bild från din dator som du vill infoga i texten. Bilden bör vara i jpeg format, png och gif format fungerar inte ibland när de är sparade med cmyk färgformat.", false, true)).style("font-size: 10px;").build());
        builder.add(Break.I2);

        Form.Builder form = new Form.Builder("upload_form", "", "upload_image").id("upload_form");
        form.encodingType(EncodingType.MULTIPART);

        form.add(new FileUpload.Builder("image_uploader", "image_uploader", Arrays.asList(MimeType.JPG)).onChange("document.upload_form.submit();").build());

        builder.add(form.build());

        htmlBuilder.add(builder.build());
    }

    @Override
    protected UserSession createUserSession(HttpServletRequest req, HttpServletResponse resp) throws StopServletExecutionException {

        return null;
    }

    @Override
    protected void mayAccessPageSpecific(UserSession session, TinyMCEImageHandlerPageModel pageModel, HttpServletResponse resp) throws IOException, NotAuthorizedException, UserBlockedException, StopServletExecutionException {

        if(!session.getUser().hasAnyUserRole(Arrays.asList(Role.MEMBER, Role.VIP_MEMBER))) {

            throw new NotAuthorizedException();
        }
    }

    @Override
    protected void handleManageablePageException(UserSession session, ManageableException exception, Html.Builder htmlBuilder) throws ServletException, IOException {

    }

    @Override
    public ComponentGroup getPageSpecificHtml(TinyMCEImageHandlerPageModel model, UserSession session) {

        ComponentGroup.Builder builder = new ComponentGroup.Builder();

        return builder.build();
    }
}
