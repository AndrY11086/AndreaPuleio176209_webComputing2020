package model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	 private String from = "andreapuleio@yahoo.it";
	 private String username = "andreapuleio";
	 private String password = "irbkmukhtmhzgmav";
	 
	 public void sendEmail(String to, String subject, String text) throws AddressException, MessagingException {
	   /***** creiamo un oggetto di tipo Properties che conterrà i parametri di configurazione per la connessione al mail server *****/
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtp.mail.yahoo.com");
	   props.put("mail.smtp.port", "587");
	 
	  /******* Creiamo una connessione al mail server ********/
	  Session session = Session.getInstance(
	     props,
	     new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {
	          return new PasswordAuthentication(username, password);
	       }
	    });
	 
	  /****** creiamo il messaggio impostando, destinatari, oggetto e contenuto del messaggio ******/
	  Message message = new MimeMessage(session);
	  message.setFrom(new InternetAddress(from));
	  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	  message.setSubject(subject);
	  message.setText(text);
	 
	  /***** INVIAMO L'EMAIL! ******/
	  Transport.send(message);
	 }
	}
