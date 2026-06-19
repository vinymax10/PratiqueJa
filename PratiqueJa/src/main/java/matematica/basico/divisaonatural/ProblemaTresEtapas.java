package matematica.basico.divisaonatural;

import java.util.Random;

import matematica.Nomes;

// Divisão em dois passos: total $a dividido em $b grupos e cada grupo em $c subgrupos.
// porDep = a / b; resultado = porDep / c. Placeholders: $a (total), $b, $c, $nomeM/$nomeF.
public class ProblemaTresEtapas
{
	public String texto;
	public TipoTresEtapas tipo;

	public int a, b, c, q;
	public String nomeM, nomeF;

	public ProblemaTresEtapas(String texto)
	{
		this(texto, TipoTresEtapas.DOIS_PASSOS);
	}

	public ProblemaTresEtapas(String texto, TipoTresEtapas tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		c = 2 + rand.nextInt(5);
		b = 3 + rand.nextInt(6);
		q = 20 + rand.nextInt(30);
		a = b * c * q;
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaTresEtapas clone()
	{
		return new ProblemaTresEtapas(texto, tipo);
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
		return q;
	}

	public String resolucao()
	{
		int porDep = a / b;
		String res = "Resolvemos em dois passos: \\(\\\\\\)";
		res += "Passo 1 — dividimos o total pelo número de grupos: \\(\\\\\\)";
		res += "\\(" + a + " \\div " + b + " = " + porDep + "\\) \\(\\\\\\)";
		res += "Passo 2 — dividimos cada grupo pelo número de subgrupos: \\(\\\\\\)";
		res += "\\(" + porDep + " \\div " + c + " = \\mathbf{" + q + "}\\)";
		return res;
	}
}
