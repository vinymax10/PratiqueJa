package matematica.basico.subtracaonatural;

import java.util.Random;

import matematica.Nomes;

// Lógica única do problema de diferença contextualizada: a maior quantidade (a)
// menos a menor (b). Gera valores por nível, substitui placeholders, calcula e resolve.
public class ProblemaDiferenca
{
	public String texto;            // template com $a (maior), $b (menor) e opcional $nomeM/$nomeF
	public TipoDiferenca tipo;

	public int a, b;
	public String nomeM, nomeF;

	public ProblemaDiferenca(String texto)
	{
		this(texto, TipoDiferenca.DIFERENCA);
	}

	public ProblemaDiferenca(String texto, TipoDiferenca tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	// Garante a > b. O nível controla a faixa de valores.
	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		int min, range, gap;
		switch(nivel)
		{
			case 1:  min = 10;  range = 20;  gap = 20;  break;
			case 2:  min = 20;  range = 40;  gap = 50;  break;
			default: min = 100; range = 400; gap = 400; break;
		}
		b = min + rand.nextInt(range);
		a = b + 5 + rand.nextInt(gap);
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaDiferenca clone()
	{
		return new ProblemaDiferenca(texto, tipo);
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
		switch(tipo)
		{
			default: // DIFERENCA
				return a - b;
		}
	}

	public String resolucao()
	{
		int dif = resultado();
		String res = "Para encontrar a diferença entre duas quantidades, subtraímos a menor da maior: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = \\mathbf{" + dif + "}\\)";
		return res;
	}
}
