package soe.boe.eis.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class MailController {

	/**
	 * generats the mail for new users
	 * 
	 * @param newUserObject
	 *            the datas of the user
	 * @param secCode
	 *            the secCode of the user
	 */
	public static void sendNewUserMail(UserObject newUserObject, String secCode) {

		String title = "Ihr Registrierung bei der TH-Parkplatz-App";
		String sender = "eis@system-systeme.de";
		String reciever = newUserObject.getEmail();
		String messageContent = "<a href=\"http://system-systeme.de/projects/eis/eis.php?mode=reg&mail="
				+ newUserObject.getEmail() + "&secCode=" + secCode + "\">Account aktivieren</a>";
		final String mailServerUserName = "eis";
		final String mailServerPassword = "Schinkenwurst";

		sendMail(mailServerUserName, mailServerPassword, sender, reciever, title, messageContent);

	}

	/**
	 * generats the mail for fogotten passwords
	 * 
	 * @param mailAddress
	 *            the mailAddress of the user
	 * @param secCode
	 *            the secCode of the user
	 */
	public static void sendFergotPasswordMail(String mailAddress, String secCode) {

		String title = "Ihr vergessene Mailadresse bei der TH-Parkplatz-App";
		String sender = "eis@system-systeme.de";
		String reciever = mailAddress;
		String messageContent = "<a href=\"http://system-systeme.de/projects/eis/eis.php?mode=fpw1&mail=" + mailAddress
				+ "&secCode=" + secCode + "\">Neues Passwort angeben</a>";
		final String mailServerUserName = "eis";
		final String mailServerPassword = "Schinkenwurst";

		sendMail(mailServerUserName, mailServerPassword, sender, reciever, title, messageContent);

	}

	/**
	 * send a mail with the given datas
	 * 
	 * @param mailServerUserName
	 *            the user name for the mail account
	 * @param mailServerPassword
	 *            the password for the mail account
	 * @param sender
	 *            the mailAddress of the programm
	 * @param reciever
	 *            the mailAddress to send to
	 * @param title
	 *            the title of the mail
	 * @param messageContent
	 *            the content of the mail
	 */
	private static void sendMail(final String mailServerUserName, final String mailServerPassword, String sender,
			String reciever, String title, String messageContent) {

		Properties properties = System.getProperties();
		properties.setProperty("mail.host", "mail.system-systeme.de");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");
		// properties.put("", "");

		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");

		Session session = javax.mail.Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailServerUserName, mailServerPassword);
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {

			message.setFrom(sender);
			message.setRecipients(Message.RecipientType.TO, reciever);
			message.setSubject(title);
			message.setContent(messageContent, "text/html");

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
