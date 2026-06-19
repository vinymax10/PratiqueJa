package matematica.basico.subtracaonatural;

import java.util.Random;

import matematica.Nomes;

// Subtração em dois passos: total $a, retira-se $b e depois $c; sobra a - b - c.
// Placeholders: $a (total), $b, $c (retiradas) e $nomeM/$nomeF.
public class ProblemaTresEtapas
{
	public String texto;
	public TipoTresEtapas tipo;

	public int a, b, c;
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

	// a = b + c + folga, garantindo a - b - c > 0. O nível controla a faixa.
	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		switch(nivel)
		{
			case 2:
				b = 10 + rand.nextInt(25);  c = 5 + rand.nextInt(20);  a = b + c + 10 + rand.nextInt(30);  break;
			default: // 3
				b = 100 + rand.nextInt(200); c = 50 + rand.nextInt(150); a = b + c + 100 + rand.nextInt(300); break;
		}
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
		return a - b - c;
	}

	public String resolucao()
	{
		int ab = a - b;
		int resultado = ab - c;
		String res = "Subtraímos em dois passos: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = " + ab + "\\) \\(\\\\\\)";
		res += "\\(" + ab + " - " + c + " = \\mathbf{" + resultado + "}\\)";
		return res;
	}
}
