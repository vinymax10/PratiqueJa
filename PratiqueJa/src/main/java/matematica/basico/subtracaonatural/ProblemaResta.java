package matematica.basico.subtracaonatural;

import java.util.Random;

import matematica.Nomes;

// Subtração "resta": total $a, retira-se $b, sobra a - b. Placeholders: $a, $b, $nomeM/$nomeF.
public class ProblemaResta
{
	public String texto;
	public TipoResta tipo;

	public int a, b;
	public String nomeM, nomeF;

	public ProblemaResta(String texto)
	{
		this(texto, TipoResta.RESTA);
	}

	public ProblemaResta(String texto, TipoResta tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	// Garante 0 < b < a. O nível controla a faixa de valores.
	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		switch(nivel)
		{
			case 1:  a = 20 + rand.nextInt(40);  b = 5 + rand.nextInt(Math.max(1, a / 3));  break;
			case 2:  a = 40 + rand.nextInt(60);  b = 10 + rand.nextInt(30); if(b >= a) b = a / 2;  break;
			default: a = 300 + rand.nextInt(700); b = 50 + rand.nextInt(a / 2);  break;
		}
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaResta clone()
	{
		return new ProblemaResta(texto, tipo);
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
		return a - b;
	}

	public String[] resolucao()
	{
		int c = resultado();
		return new String[]
		{
			"Para encontrar o que resta, subtraímos:",
			"\\(" + a + " - " + b + " = \\mathbf{" + c + "}\\)"
		};
	}
}
