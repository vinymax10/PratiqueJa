package matematica.basico.multiplicacaodivisaointeiro;

import java.util.Random;

import matematica.Nomes;

// Multiplicação de inteiros em contexto: grandeza negativa de magnitude $m repetida $n vezes.
// resultado = -(m * n). $m e $n são positivos no enunciado; a negatividade vem do contexto.
public class ProblemaIntMult
{
	public String texto;
	public TipoIntMult tipo;

	public int m, n;
	public String nomeM, nomeF;

	public ProblemaIntMult(String texto)
	{
		this(texto, TipoIntMult.GRANDEZA_NEGATIVA);
	}

	public ProblemaIntMult(String texto, TipoIntMult tipo)
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
			case 1:  m = 2 + rand.nextInt(4);   n = 2 + rand.nextInt(5);   break;
			case 2:  m = 2 + rand.nextInt(8);   n = 2 + rand.nextInt(7);   break;
			default: m = 5 + rand.nextInt(16);  n = 4 + rand.nextInt(10);  break;
		}
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaIntMult clone()
	{
		return new ProblemaIntMult(texto, tipo);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		pergunta = pergunta.replace("$m", "" + m);
		pergunta = pergunta.replace("$n", "" + n);
		if(pergunta.contains("$nomeM"))
			pergunta = pergunta.replace("$nomeM", nomeM);
		if(pergunta.contains("$nomeF"))
			pergunta = pergunta.replace("$nomeF", nomeF);
		return pergunta;
	}

	public int resultado()
	{
		return -(m * n);
	}

	public String resolucao()
	{
		int r = resultado();
		String res = "Multiplicamos a grandeza negativa pela quantidade: \\(\\\\\\)";
		res += "\\((-" + m + ") \\times " + n + " = \\mathbf{" + r + "}\\)";
		return res;
	}
}
