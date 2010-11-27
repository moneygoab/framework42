package org.framework42.utils.services;

/**
 * Class that can be user to send simple non html mails using Java mail.
 */
public interface EMailSender {

    /**
     * Sends a non html mail to one single receiver.
     *
     * @param subject     The subject of the mail.
     * @param messageText The text in the mail.
     * @param fromAddress The email of the one sending the mail.
     * @param toAddress   The address of the receiver of the mail.
     */
    public void sendSimpleEMail(String subject, String messageText, String fromAddress, String toAddress);

}
