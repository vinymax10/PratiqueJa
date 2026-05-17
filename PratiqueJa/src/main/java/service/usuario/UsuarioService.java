package service.usuario;

import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioService
{
	private static final SecureRandom RANDOM = new SecureRandom();

	public String hashPassword(String senha)
	{
		return BCrypt.hashpw(senha, BCrypt.gensalt(12));
	}

	public String gerarSenhaAleatoria()
	{
		String[] carct = { "0","1","2","3","4","5","6","7","8","9",
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
			"q","r","s","t","u","v","w","x","y","z",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
			"Q","R","S","T","U","V","W","X","Y","Z" };
		StringBuilder str = new StringBuilder();
		str.append(carct[RANDOM.nextInt(10)]);
		str.append(carct[10 + RANDOM.nextInt(26)]);
		str.append(carct[36 + RANDOM.nextInt(26)]);
		for(int i = 0; i < 7; i++)
			str.append(carct[RANDOM.nextInt(carct.length)]);
		return str.toString();
	}
}
