package Infra;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.Normalizer;

import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import Modelo.DocumentoFile;
import Modelo.Email;

public class CommonsEmail
{
	static String host = "smtp.gmail.com";
	static int port = 465;
	static String usuario = "pratiqueja.com@gmail.com";
//	Senha de app
	static String senha = "wmgsacflxogbtwtv";
	
	public static boolean mandarEmailSimples(Email emailPersit) 
	{
		SimpleEmail email = new SimpleEmail();
		try
		{
			email.setCharset("UTF8");
			email.setDebug(false);
			email.setHostName(host);
			email.setSmtpPort(port);
			email.setAuthenticator(new DefaultAuthenticator("pratiqueja.com", senha));
			email.setSSLOnConnect(true);
			email.setFrom(usuario); // será passado o email que você fará a autenticação
			email.addTo(emailPersit.getDestinatario().trim().split(" "));
			email.setSubject(emailPersit.getAssunto());
			email.setMsg(emailPersit.getMensagem());
			email.send();
			return true;
		}
		catch(EmailException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean mandarMultiPartEmail(Email emailPersit)
	{
		MultiPartEmail email = new MultiPartEmail();
		try
		{
			email.setCharset("UTF-8");
			email.setDebug(false);
			email.setHostName(host);
			email.setSmtpPort(port);
			email.setAuthenticator(new DefaultAuthenticator("pratiqueja.com", senha));
			email.setSSLOnConnect(true);
			email.setFrom(usuario); // será passado o email que você fará a autenticação
			email.addTo(emailPersit.getDestinatario().trim().split(" "));
			email.setSubject(emailPersit.getAssunto());
			email.setMsg(emailPersit.getMensagem());

			for(DocumentoFile documentoFile : emailPersit.getDocumentosFile())
			{
				int blobLength = (int) documentoFile.getFile().length();
				byte[] fileData = documentoFile.getFile().getBytes(1, blobLength);

				// Adiciona o anexo
				ByteArrayDataSource dataSource = new ByteArrayDataSource(fileData, ValidacaoFile.getMimeType(documentoFile.getEndDocumentacao()));
				String nomeArquivo = Normalizer.normalize(documentoFile.getEndDocumentacao(), Normalizer.Form.NFD)
		        .replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); // remove acentos
				System.out.println("nomeArquivo: "+nomeArquivo);

				email.attach(dataSource, nomeArquivo, "Anexo enviado pelo sistema");
			}

			email.send();
			return true;
			
		}
		
		catch(EmailException e)
		{
			System.out.println("Causa: " + e.getCause());
			System.out.println("Mensagem: " +	e.getMessage());
			return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
