package com.ecommerce.serviceImpl;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
/*
 * This class is used for email sending and send otp on emaill
 * 
 */
@Service
@RequiredArgsConstructor
public class EmailService {
	private final JavaMailSender javaMailSender;
	/*
	 * send verification otp on email id
	 */
	public void sendVerificationOtpEmail (String userEmail,String otp,String subject,String text)throws Exception {
		try {
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper miemMessageHelper=new MimeMessageHelper(mimeMessage,"utf-8");
		miemMessageHelper.setText(text);
		miemMessageHelper.setSubject(subject);
		miemMessageHelper.setTo(userEmail);
		javaMailSender.send(mimeMessage);
		}catch(MailException e) {
			System.out.println(e+"  ==========================");
			throw new MailSendException("failed to send email");
		}
		
	}

}
