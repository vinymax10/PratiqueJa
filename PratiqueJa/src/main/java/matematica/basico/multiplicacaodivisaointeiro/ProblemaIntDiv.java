package matematica.basico.multiplicacaodivisaointeiro;

import java.util.Random;

import matematica.Nomes;

// Divisão de inteiros em contexto: grandeza total negativa de magnitude $t repartida entre
// $n partes iguais. resultado = -(t / n). $t é múltiplo de $n; ambos positivos no enunciado.
public class ProblemaIntDiv
{
	public String texto;
	public TipoIntDiv tipo;

	public int t, n;
	public String nomeM, nomeF;

	public ProblemaIntDiv(String texto)
	{
		this(texto, TipoIntDiv.REPARTIR_NEGATIVO);
	}

	public ProblemaIntDiv(String texto, TipoIntDiv tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		int perMag;
		switch(nivel)
		{
			case 1:  perMag = 2 + rand.nextInt(4);   n = 2 + rand.nextInt(5);   break;
			case 2:  perMag = 2 + rand.nextInt(8);   n = 2 + rand.nextInt(7);   break;
			default: perMag = 5 + rand.nextInt(16);  n = 4 + rand.nextInt(9);   break;
		}
		t = perMag * n;   // garante divisão exata
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaIntDiv clone()
	{
		return new ProblemaIntDiv(texto, tipo);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		pergunta = pergunta.replace("$t", "" + t);
		pergunta = pergunta.replace("$n", "" + n);
		if(pergunta.contains("$nomeM"))
			pergunta = pergunta.replace("$nomeM", nomeM);
		if(pergunta.contains("$nomeF"))
			pergunta = pergunta.replace("$nomeF", nomeF);
		return pergunta;
	}

	public int resultado()
	{
		return -(t / n);
	}

	public String[] resolucao()
	{
		int r = resultado();
		return new String[] {
			"A grandeza total negativa é representada como \\(-" + t + "\\):",
			"\\((-" + t + ") \\div " + n + " = \\mathbf{" + r + "}\\)"
		};
	}
}
