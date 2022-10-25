package MobileShop.service;

import javax.mail.MessagingException;

import MobileShop.Entity.Mail;

public interface MailerService {
void send(Mail mail) throws MessagingException;
	
	void send(String to, String subject, String body) throws MessagingException;
}
