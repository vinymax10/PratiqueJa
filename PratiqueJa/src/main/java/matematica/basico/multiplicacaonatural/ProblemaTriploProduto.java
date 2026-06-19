package matematica.basico.multiplicacaonatural;

import java.util.Random;

import matematica.Nomes;

// Produto de três fatores em dois passos: total = (a x b) x c. $a é a base grande e
// $b, $c fatores pequenos. Cada template carrega a unidade exibida na resolução.
public class ProblemaTriploProduto
{
	public String texto;
	public String unidade;   // ex.: "\\,\\text{peças}" ou ""
	public TipoTriploProduto tipo;

	public int a, b, c;
	public String nomeM, nomeF;

	public ProblemaTriploProduto(String texto, String unidade)
	{
		this(texto, unidade, TipoTriploProduto.TRIPLO);
	}

	public ProblemaTriploProduto(String texto, String unidade, TipoTriploProduto tipo)
	{
		super();
		this.texto = texto;
		this.unidade = unidade;
		this.tipo = tipo;
	}

	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		a = 100 + rand.nextInt(300);
		b = 3 + rand.nextInt(7);
		c = 3 + rand.nextInt(7);
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaTriploProduto clone()
	{
		return new ProblemaTriploProduto(texto, unidade, tipo);
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
		return a * b * c;
	}

	public String resolucao()
	{
		int passo1 = a * b;
		int total = passo1 * c;
		String res = "Passo 1: \\(\\\\\\)";
		res += "\\(" + a + " \\times " + b + " = " + passo1 + unidade + "\\) \\(\\\\\\)";
		res += "Passo 2: \\(\\\\\\)";
		res += "\\(" + passo1 + " \\times " + c + " = \\mathbf{" + total + "}" + unidade + "\\)";
		return res;
	}
}
