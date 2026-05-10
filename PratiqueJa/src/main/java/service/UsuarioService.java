package service;

import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UsuarioService
{
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
		Random rand = new Random();
		StringBuilder str = new StringBuilder();
		str.append(carct[rand.nextInt(10)]);
		str.append(carct[10 + rand.nextInt(26)]);
		str.append(carct[36 + rand.nextInt(26)]);
		for(int i = 0; i < 7; i++)
			str.append(carct[rand.nextInt(carct.length)]);
		return str.toString();
	}
}
