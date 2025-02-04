package rimenergyapi.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import rimenergyapi.service.SmsService;

@Component
public class SmsServiceImpl implements SmsService {

	@Value("${twilio.account.sid}")
	private String accountSid;

	@Value("${twilio.account.token}")
	private String accountToken;

	@Value("${twilio.account.number}")
	private String accountNumber;

	@Override
	@Async
	public void sendMessage(String to, String text) {
		// Find your Account Sid and Auth Token at twilio.com/console
		Twilio.init(accountSid, accountToken);
		Message.creator(new PhoneNumber(to), new PhoneNumber(accountNumber), text).create();
	}

}
