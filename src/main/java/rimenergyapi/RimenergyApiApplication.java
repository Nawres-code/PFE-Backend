package rimenergyapi;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

import rimenergyapi.service.EmailService;
import rimenergyapi.service.SmsService;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "rimenergyapi")
@EnableAsync
public class RimenergyApiApplication implements CommandLineRunner {

	@Autowired
	private EmailService emailService;

	@Autowired
	private SmsService smsService;

	Logger logger = LoggerFactory.getLogger(RimenergyApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RimenergyApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RIMENERGY API ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		String deployMessage = "The App was deployed in the address : " + InetAddress.getLocalHost().getHostAddress()
				+ " hostname : " + InetAddress.getLocalHost().getHostName() + " Remote address : "
				+ InetAddress.getLoopbackAddress().getHostAddress() + " "
				+ InetAddress.getLoopbackAddress().getHostName() + " at "
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		//111this.emailService.sendMessage("taboubic@gmail.com", "Application was deployed", deployMessage);

		//this.smsService.sendMessage("+212 610-844460", deployMessage);
	}

}
