package Infra;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;

public class CommonsEmail
{
	static String host = "smtp.gmail.com";
	static int port = 465;
	static String usuario = "pratiqueja.com@gmail.com";
//		Senha de app
	static String senha = "wmgsacflxogbtwtv";

	public static void mandarEmailSimples(Email emailPersit)
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");

		Session session = Session.getInstance(props, new Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(usuario, senha);
			}
		});

		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usuario));
			message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(emailPersit.getDestinatario()));
			message.setSubject(emailPersit.getAssunto());
			message.setText(emailPersit.getMensagem());

			Transport.send(message);
			System.out.println("Email enviado com sucesso!");

		}
		catch(MessagingException e)
		{
			throw new RuntimeException(e);
		}
	}

//			SimpleEmail email = new SimpleEmail();
//			try
//			{
//				email.setCharset("UTF8");
//				email.setDebug(true);
//				email.setHostName(host);
//				email.setSmtpPort(port);
//				email.setAuthenticator(new DefaultAuthenticator("orcamentodigitalsuporte", senha));
//				email.setSSLOnConnect(true);
//				email.setStartTLSEnabled(false);
//				email.setFrom(usuario); // será passado o email que você fará a autenticação
//				email.addTo(emailPersit.getDestinatario().trim().split(" "));
//				email.setSubject(emailPersit.getAssunto());
//				email.setMsg(emailPersit.getMensagem());
//				email.send();
//				return true;
//			}
//			catch(EmailException e)
//			{
//				e.printStackTrace();
//				return false;
//			}
//		}

	public static void main(String[] args)
	{
		Email emailPersit = new Email();
		emailPersit.setAssunto("teste2");
		emailPersit.setDestinatario("vinymax10@gmail.com");
		emailPersit.setMensagem("teste22");
		CommonsEmail.mandarEmailSimples(emailPersit);
	}

}
