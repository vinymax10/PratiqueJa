package infra;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.activation.DataHandler;
import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import modelo.email.Email;
import service.email.EmailParaEnvio;

public class CommonsEmail
{
	private static final Logger LOG = LoggerFactory.getLogger(CommonsEmail.class);

	static String host = "smtp.gmail.com";
	static int port = 465;
	static String usuario = "pratiqueja.com@gmail.com";
//		Senha de app
	static String senha = "wmgsacflxogbtwtv";

	private static Session criarSession()
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");

		return Session.getInstance(props, new Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(usuario, senha);
			}
		});
	}

	/**
	 * Envia um e-mail (com eventuais anexos) via SMTP. Propaga
	 * {@link MessagingException} em caso de falha, para que o chamador possa
	 * registrar a tentativa.
	 */
	public static void mandarEmail(EmailParaEnvio email) throws MessagingException
	{
		MimeMessage message = new MimeMessage(criarSession());
		message.setFrom(new InternetAddress(usuario));
		message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getDestinatario()));
		message.setSubject(email.getAssunto(), "UTF-8");

		if(email.getAnexos().isEmpty())
		{
			message.setText(email.getMensagem(), "UTF-8");
		}
		else
		{
			MimeMultipart multipart = new MimeMultipart();

			MimeBodyPart corpo = new MimeBodyPart();
			corpo.setText(email.getMensagem(), "UTF-8");
			multipart.addBodyPart(corpo);

			for(EmailParaEnvio.Anexo anexo : email.getAnexos())
			{
				String nome = anexo.getNome() != null ? anexo.getNome() : "anexo";
				MimeBodyPart parte = new MimeBodyPart();
				parte.setDataHandler(new DataHandler(new ByteArrayDataSource(anexo.getDados(), tipoMime(nome))));
				parte.setFileName(nome);
				multipart.addBodyPart(parte);
			}

			message.setContent(multipart);
		}

		Transport.send(message);
	}

	private static String tipoMime(String nome)
	{
		String n = nome.toLowerCase();
		if(n.endsWith(".png")) return "image/png";
		if(n.endsWith(".jpg") || n.endsWith(".jpeg")) return "image/jpeg";
		if(n.endsWith(".pdf")) return "application/pdf";
		return "application/octet-stream";
	}

	public static void mandarEmailSimples(Email emailPersit)
	{
		try
		{
			MimeMessage message = new MimeMessage(criarSession());
			message.setFrom(new InternetAddress(usuario));
			message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(emailPersit.getDestinatario()));
			message.setSubject(emailPersit.getAssunto());
			message.setText(emailPersit.getMensagem());

			Transport.send(message);
			LOG.info("Email enviado com sucesso!");

		}
		catch(MessagingException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args)
	{
		Email emailPersit = new Email();
		emailPersit.setAssunto("teste2");
		emailPersit.setDestinatario("vinymax10@gmail.com");
		emailPersit.setMensagem("teste22");
		CommonsEmail.mandarEmailSimples(emailPersit);
	}

}
