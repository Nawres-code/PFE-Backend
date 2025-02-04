package rimenergyapi.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import rimenergyapi.exception.model.CustomException;
import rimenergyapi.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Async
	public void sendMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	@Override
	@Async
	public void sendHtmlMessage(String to, String subject, String html) {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setContent(html, "text/html");
			helper.setTo(to);
			helper.setSubject(subject);
			emailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new CustomException("Mail Server Error - Please contact your administrator, Thank you !",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		// TODO Auto-generated method stub

	}
}
