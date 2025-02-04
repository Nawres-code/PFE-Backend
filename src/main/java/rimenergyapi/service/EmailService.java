package rimenergyapi.service;

public interface EmailService {

	void sendMessage(String to, String subject, String text);

	void sendHtmlMessage(String to, String subject, String html);

	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

}