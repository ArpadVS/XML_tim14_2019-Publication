package publications.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Value("${editor.mail}")
	private String editorsMail;
	
	private JavaMailSender javaMailSender;
	
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Async
	public void sendPaperSubmissionToEditor(String author, String paperName, String coverLetterText)
			throws MailException, InterruptedException {

		System.out.println("Sending sendPaperSubmissionToEditor e-mail from " + author);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(editorsMail);
		mail.setFrom("xmlprojekat.2020@gmail.com");
		mail.setSubject("Scientific paper submission by: " + author + ", paper title: " + paperName);
		mail.setText("You have new scientific paper submission by: " + author + ", tile of the paper: " + paperName
				+ "." + "\nCover letter:\n" + coverLetterText);
		javaMailSender.send(mail);
		System.out.println("E-mail endPaperSubmissionToEditor sent!");
	}

	
}
