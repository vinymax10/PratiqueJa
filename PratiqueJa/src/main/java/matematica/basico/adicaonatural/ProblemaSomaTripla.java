package matematica.basico.adicaonatural;

import java.util.Random;

import matematica.Nomes;

// Soma contextualizada de três parcelas, resolvida em dois passos: (a + b) + c.
// Placeholders: $a, $b, $c (parcelas) e $nomeM/$nomeF.
public class ProblemaSomaTripla
{
	public String texto;
	public TipoSomaTripla tipo;

	public int a, b, c;
	public String nomeM, nomeF;

	public ProblemaSomaTripla(String texto)
	{
		this(texto, TipoSomaTripla.TRES_PARCELAS);
	}

	public ProblemaSomaTripla(String texto, TipoSomaTripla tipo)
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
			case 2:
				a = 15 + rand.nextInt(35);  b = 10 + rand.nextInt(30);  c = 5 + rand.nextInt(25);  break;
			default: // 3
				a = 100 + rand.nextInt(300); b = 100 + rand.nextInt(300); c = 50 + rand.nextInt(200); break;
		}
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaSomaTripla clone()
	{
		return new ProblemaSomaTripla(texto, tipo);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		pergunta = pergunta.replace("$a", "" + a);
		pergunta = pergunta.replace("$b", "" + b);
		pergunta = pergunta.replace("$c", "" + c);
		if(pergunta.contains("$nomeM"))
			pergunta = pergunta.replace("$nomeM", nomeM);
		if(pergunta.contains("$nomeF"))
			pergunta = pergunta.replace("$nomeF", nomeF);
		return pergunta;
	}

	public int resultado()
	{
		return a + b + c;
	}

	public String resolucao()
	{
		int ab = a + b;
		int soma = ab + c;
		String res = "Somamos as três quantidades em dois passos: \\(\\\\\\)";
		res += "\\(" + a + " + " + b + " = " + ab + "\\) \\(\\\\\\)";
		res += "\\(" + ab + " + " + c + " = \\mathbf{" + soma + "}\\)";
		return res;
	}
}
