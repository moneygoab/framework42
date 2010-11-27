package org.framework42.utils.services.impl;

import org.apache.log4j.Logger;
import org.framework42.utils.services.EMailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public enum EMailSenderImpl implements EMailSender {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.framework42");

    private final String mailServerURL;

    private EMailSenderImpl() {

        mailServerURL = "localhost";

    }

    public void sendSimpleEMail(String subject, String messageText, String fromAddress, String toAddress) {

        Properties MAIL_SETTINGS = new Properties();
        MAIL_SETTINGS.setProperty("mail.transport.protocol", "smtp");
        MAIL_SETTINGS.setProperty("mail.host", mailServerURL);

        try {

            Session mailSession = Session.getDefaultInstance(MAIL_SETTINGS, null);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(subject);
            message.setContent(messageText, "text/plain");
            message.setFrom(new InternetAddress(fromAddress));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toAddress));

            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();

        } catch (NoSuchProviderException e) {
            logger.fatal("EMailSender.sendSimpleEMail: " + e);
            throw new RuntimeException();
        } catch (MessagingException e) {
            logger.fatal("EMailSender.sendSimpleEMail: " + e);
            throw new RuntimeException();
        }

    }

}
