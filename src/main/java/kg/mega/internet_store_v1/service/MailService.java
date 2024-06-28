package kg.mega.internet_store_v1.service;

public interface MailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
