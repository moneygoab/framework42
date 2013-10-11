package org.framework42.web.plugins.TinyMCEImageHandler;

import org.apache.commons.fileupload.FileItem;
import org.framework42.exceptions.ManageableException;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.i18n.I18N;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagelogic.FormListener;
import org.framework42.web.session.UserSession;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class TinyMCEImageHandlerFormListener extends FormListener<UserSession, TinyMCEImageHandlerPageModel> {

    public TinyMCEImageHandlerFormListener() {
        super("upload_image", "org.framework42.web");
    }

    @Override
    protected void handleFormEvent(HttpServletRequest req, HttpServletResponse resp, UserSession session, TinyMCEImageHandlerPageModel pageModel) throws NotAuthorizedException, ManageableException, StopServletExecutionException {

        if(pageModel.getFileItemList().size()>0) {

            for(FileItem fileItem: pageModel.getFileItemList()) {

                String fileName = session.getUser().getId()+System.currentTimeMillis()+".jpg";

                if(!new File("/var/lib/tomcat6/webapps/poeter/media/img/user/blogg/"+session.getUser().getId()).exists()) {
                //if(!new File("/home/fredrik/Documents/dev/PoeterV3_Project/out/artifacts/poeter_web_Web_exploded/media/img/user/blogg/"+session.getUser().getId()+"/").exists()) {

                        boolean created = new File("/var/lib/tomcat6/webapps/poeter/media/img/user/blogg/"+session.getUser().getId()+"/").mkdir();
                        //boolean created = new File("/home/fredrik/Documents/dev/PoeterV3_Project/out/artifacts/poeter_web_Web_exploded/media/img/user/blogg/"+session.getUser().getId()+"/").mkdir();
                        if(!created) {logger.error("Couldn't create blog image directory for user "+session.getUser().getId()+"!");}
                }

                //File uploadedFile = new File("/home/fredrik/Documents/dev/PoeterV3_Project/out/artifacts/poeter_web_Web_exploded/media/img/user/blogg/"+session.getUser().getId()+"/"+fileName);
                File uploadedFile = new File("/var/lib/tomcat6/webapps/poeter/media/img/user/blogg/"+session.getUser().getId()+"/"+fileName);
                try {

                    byte[] imageData = fileItem.get();
                    InputStream in = new ByteArrayInputStream(imageData);
                    BufferedImage image = ImageIO.read(in);

                    if(image == null) {
                        pageModel.setError(I18N.INSTANCE.get("error_wrong_file_type", session.getLocale(), fileItem.getContentType()));
                    }

                    if(image.getWidth()>image.getHeight()) {
                        if(image.getWidth()>600) {

                            float ratio = 600.0f/image.getWidth();
                            int height = (int)(image.getHeight()*ratio);

                            BufferedImage newImage = new BufferedImage(600, height,
                                    BufferedImage.TYPE_INT_RGB);
                            Graphics2D g = newImage.createGraphics();
                            g.drawImage(image, 0, 0, 600, height, null);
                            g.dispose();

                            image = newImage;

                        }
                    }

                    ImageIO.write(image, "jpeg", uploadedFile);

                    pageModel.setImageUploaded(true);
                    pageModel.setFileUrl("media/img/user/blogg/"+session.getUser().getId()+"/"+fileName);

                } catch (IllegalArgumentException e) {

                    logger.info(e);
                    pageModel.setError(I18N.INSTANCE.get("error_wrong_file_type", session.getLocale(), fileItem.getContentType()));
                } catch(Exception e) {
                    logger.error(e);
                }

            }


        }
    }

}
