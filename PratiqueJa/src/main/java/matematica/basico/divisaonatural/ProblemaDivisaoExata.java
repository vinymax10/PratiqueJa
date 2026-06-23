package matematica.basico.divisaonatural;

import java.util.Random;

import matematica.Nomes;

// Divisão exata: total $a repartido em $b partes iguais; cada parte recebe a / b (sem resto).
// $a é o dividendo (múltiplo de $b) e $b o divisor. Placeholders: $a, $b, $nomeM/$nomeF.
public class ProblemaDivisaoExata
{
	public String texto;
	public TipoDivisaoExata tipo;

	public int a, b, q;
	public String nomeM, nomeF;

	public ProblemaDivisaoExata(String texto)
	{
		this(texto, TipoDivisaoExata.EXATA);
	}

	public ProblemaDivisaoExata(String texto, TipoDivisaoExata tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		switch(nivel)
		{
			case 1:  q = 2 + rand.nextInt(9);   b = 2 + rand.nextInt(9);   break;
			case 2:  q = 5 + rand.nextInt(16);  b = 3 + rand.nextInt(7);   break;
			default: q = 10 + rand.nextInt(41); b = 4 + rand.nextInt(9);   break;
		}
		a = q * b;
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaDivisaoExata clone()
	{
		return new ProblemaDivisaoExata(texto, tipo);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		pergunta = pergunta.replace("$a", "" + a);
		pergunta = pergunta.replace("$b", "" + b);
		if(pergunta.contains("$nomeM"))
			pergunta = pergunta.replace("$nomeM", nomeM);
		if(pergunta.contains("$nomeF"))
			pergunta = pergunta.replace("$nomeF", nomeF);
		return pergunta;
	}

	public int resultado()
	{
		return q;
	}

	public String[] resolucao()
	{
		return new String[]
		{
			"Dividimos o total pelo número de partes iguais:",
			"\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\)"
		};
	}
}
