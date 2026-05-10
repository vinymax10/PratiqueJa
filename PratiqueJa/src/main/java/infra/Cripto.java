package infra;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cripto
{
	public static String criptografar(String valor)
	{
		try
		{
			String chave = "4314368264921487"; // 16 chars (AES-128)
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(chave.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getUrlEncoder().encodeToString(cipher.doFinal(valor.getBytes()));
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static String descriptografar(String valorCriptografado)
	{
		try
		{
			String chave = "4314368264921487";
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(chave.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decoded = Base64.getUrlDecoder().decode(valorCriptografado);
			return new String(cipher.doFinal(decoded));
		}
		catch(Exception e)
		{
			throw new RuntimeException("Chave inválida.");
		}
	}

}
