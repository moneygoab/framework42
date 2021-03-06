package org.framework42.utils.services.impl;

import org.framework42.utils.services.MailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public enum GoogleMailSender implements MailSender {

    INSTANCE;

    @Override
    public void sendMail(String userName, String password, String toAddress, String subject, String message) {


        Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session mailSession = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {

                            return new PasswordAuthentication("info@moneygo.se", "kvittraommoney");
                        }
                    });

            try {

                Message messageObject = new MimeMessage(mailSession);
                messageObject.setFrom(new InternetAddress("MoneyGo kundservice <info@moneygo.se>"));
                messageObject.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(toAddress));
                messageObject.setSubject(subject);
                messageObject.setText(message);

                Transport.send(messageObject);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

    }

    @Override
    public void sendHtmlMail(String userName, String password, String toAddress, String subject, String htmlMessage, String fallbackMessage) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        Session mailSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("info@moneygo.se", "kvittraommoney");
                    }
                });

        try {

            Message messageObject = new MimeMessage(mailSession);
            messageObject.setFrom(new InternetAddress("MoneyGo kundservice <info@moneygo.se>"));
            messageObject.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            messageObject.setSubject(subject);
            messageObject.setText(fallbackMessage);
            messageObject.setContent(htmlMessage, "text/html; charset=\"UTF-8\"");
            //messageObject.setContent(htmlMessage, "text/html");

            Transport.send(messageObject);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
