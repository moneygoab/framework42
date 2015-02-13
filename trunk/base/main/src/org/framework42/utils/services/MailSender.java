package org.framework42.utils.services;

public interface MailSender {

    public void sendMail(String userName, String password, String toAddress, String subject, String message);

    public void sendHtmlMail(String userName, String password, String toAddress, String subject, String htmlMessage, String fallbackMessage);

}
