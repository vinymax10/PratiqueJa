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

	public String[] resolucao()
	{
		String passo1 = "Pelo Princípio da Multiplicação, multiplicamos o número de possibilidades de cada etapa:";
		String passo2 = "";
		switch(tipo)
		{
			case Duas:
				passo2 = "\\(" + a + " \\cdot " + b + " = \\mathbf{" + (a*b) + "}\\)";
				break;
			case Tres:
				passo2 = "\\(" + a + " \\cdot " + b + " \\cdot " + c + " = \\mathbf{" + (a*b*c) + "}\\)";
				break;
		}

		return new String[]{passo1, passo2};
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
