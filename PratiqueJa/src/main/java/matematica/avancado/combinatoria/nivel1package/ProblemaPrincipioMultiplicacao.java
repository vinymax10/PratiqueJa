package matematica.avancado.combinatoria.nivel1package;

import java.util.Random;

public class ProblemaPrincipioMultiplicacao
{
	public String texto;

	public int a,b,c;
	public TipoPrincipioMultiplicacao tipo;
	
	public ProblemaPrincipioMultiplicacao(String texto, TipoPrincipioMultiplicacao tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		a = 3 + rand.nextInt(12);
		b = 3 + rand.nextInt(12);
		c = 3 + rand.nextInt(12);
	}

	public String resolucao()
	{
		String res = "Pelo Princípio da Multiplicação, multiplicamos o número de possibilidades de cada etapa:";
		res += "\\(\\\\\\)";
		switch(tipo)
		{
			case Duas:
				res += "\\(" + a + " \\cdot " + b + " = \\mathbf{" + (a*b) + "}\\)";
				break;
			case Tres:
				res += "\\(" + a + " \\cdot " + b + " \\cdot " + c + " = \\mathbf{" + (a*b*c) + "}\\)";
				break;
		}

		return res;
	}

	public String resultado()
	{
		switch(tipo)
		{
			case Duas: return ""+a*b;
			case Tres: return ""+a*b*c;
		}
		return "";
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$a"))
			pergunta = pergunta.replace("$a", a+"");
		
		if(pergunta.contains("$b"))
			pergunta = pergunta.replace("$b", b+"");
		
		if(pergunta.contains("$c"))
			pergunta = pergunta.replace("$c", c+"");
		
		return pergunta;
	}

	public ProblemaPrincipioMultiplicacao clone()
	{
		return new ProblemaPrincipioMultiplicacao(texto,tipo);
	}
}
