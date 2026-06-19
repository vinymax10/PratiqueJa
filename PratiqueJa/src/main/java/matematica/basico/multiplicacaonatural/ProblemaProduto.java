package matematica.basico.multiplicacaonatural;

import java.util.Random;

import matematica.Nomes;

// Multiplicação "grupos de": $a grupos (vezes) de $b unidades; total a x b.
// Placeholders: $a (quantidade de grupos), $b (tamanho do grupo) e $nomeM/$nomeF.
public class ProblemaProduto
{
	public String texto;
	public TipoProduto tipo;

	public int a, b;
	public String nomeM, nomeF;

	public ProblemaProduto(String texto)
	{
		this(texto, TipoProduto.GRUPOS);
	}

	public ProblemaProduto(String texto, TipoProduto tipo)
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
			case 1:  a = 3 + rand.nextInt(11);   b = 3 + rand.nextInt(11);   break;
			case 2:  a = 12 + rand.nextInt(79);  b = 12 + rand.nextInt(79);  break;
			default: a = 100 + rand.nextInt(900); b = 10 + rand.nextInt(90); break;
		}
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaProduto clone()
	{
		return new ProblemaProduto(texto, tipo);
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
		return a * b;
	}

	public String resolucao()
	{
		int produto = resultado();
		String res = "Identificamos uma situação de repetição: \\(" + a + "\\) grupos de \\(" + b + "\\) unidades. \\(\\\\\\)";
		res += "\\(" + a + " \\times " + b + " = \\mathbf{" + produto + "}\\)";
		return res;
	}
}
