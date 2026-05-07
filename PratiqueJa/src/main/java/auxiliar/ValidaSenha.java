package auxiliar;

public class ValidaSenha
{
	public static void validarSenha(String senha)
	{
		if(senha == null || senha.length() < 8)
			throw new IllegalArgumentException("Senha deve ter no mínimo 8 caracteres.");

		boolean temMaiuscula = false;
		boolean temMinuscula = false;
		boolean temEspecial = false;
		boolean temNumero = false;

		for(char c : senha.toCharArray())
		{
			if(Character.isUpperCase(c))
				temMaiuscula = true;
			else if(Character.isLowerCase(c))
				temMinuscula = true;
			else if(Character.isDigit(c))
				temNumero = true;
			else if(!Character.isLetterOrDigit(c))
				temEspecial = true;
		}

		if(!temMaiuscula)
			throw new IllegalArgumentException("Senha deve ter pelo menos uma letra maiúscula.");

		if(!temMinuscula)
			throw new IllegalArgumentException("Senha deve ter pelo menos uma letra minúscula.");

		if(!temNumero)
			throw new IllegalArgumentException("Senha deve ter pelo menos um número.");

		if(!temEspecial)
			throw new IllegalArgumentException("Senha deve ter pelo menos um caracter especial.");
	}

}
