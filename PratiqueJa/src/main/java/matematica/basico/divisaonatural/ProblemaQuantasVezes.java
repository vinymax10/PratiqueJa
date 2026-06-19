package matematica.basico.divisaonatural;

import java.util.Random;

import matematica.Nomes;

// Divisão como medida (exata): quantas vezes $b cabe em $a; resposta q = a / b.
// $a é o total e $b a medida que se repete. Placeholders: $a, $b, $nomeM/$nomeF.
public class ProblemaQuantasVezes
{
	public String texto;
	public TipoQuantasVezes tipo;

	public int a, b, q;
	public String nomeM, nomeF;

	public ProblemaQuantasVezes(String texto)
	{
		this(texto, TipoQuantasVezes.CABE);
	}

	public ProblemaQuantasVezes(String texto, TipoQuantasVezes tipo)
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
			case 1:  b = 2 + rand.nextInt(9);   q = 2 + rand.nextInt(9);   break;
			case 2:  b = 4 + rand.nextInt(9);   q = 6 + rand.nextInt(15);  break;
			default: b = 6 + rand.nextInt(15);  q = 10 + rand.nextInt(40); break;
		}
		a = b * q;
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaQuantasVezes clone()
	{
		return new ProblemaQuantasVezes(texto, tipo);
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

	public String resolucao()
	{
		String res = "\"Quantas vezes \\(" + b + "\\) cabe em \\(" + a + "\\)\" é a ideia central da divisão: \\(\\\\\\)";
		res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) \\(\\\\\\)";
		res += "Verificação: \\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
		return res;
	}
}
