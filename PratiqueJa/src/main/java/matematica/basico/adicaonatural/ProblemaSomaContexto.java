package matematica.basico.adicaonatural;

import java.util.Random;

import matematica.Nomes;

// Lógica única do problema de soma contextualizada: gera os valores (por nível),
// substitui os placeholders no enunciado, calcula a resposta e monta a resolução.
// O texto do enunciado vem da biblioteca TextoSomaContexto.
public class ProblemaSomaContexto
{
	public String texto;            // template com $a, $b e (opcional) $nomeM / $nomeF
	public TipoSomaContexto tipo;

	public int a, b;
	public String nomeM, nomeF;

	public ProblemaSomaContexto(String texto)
	{
		this(texto, TipoSomaContexto.TOTAL);
	}

	public ProblemaSomaContexto(String texto, TipoSomaContexto tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	// O nível controla a faixa de valores (alinhado com a Fase C):
	// n1 ~ dezenas, n2 ~ dezenas/centenas, n3 ~ centenas.
	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		int min, range;
		switch(nivel)
		{
			case 1:  min = 10;  range = 30;  break;
			case 2:  min = 20;  range = 70;  break;
			default: min = 100; range = 500; break;
		}
		a = min + rand.nextInt(range);
		b = min + rand.nextInt(range);
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaSomaContexto clone()
	{
		return new ProblemaSomaContexto(texto, tipo);
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
			default: // TOTAL
				return a + b;
		}
	}

	public String resolucao()
	{
		int soma = resultado();
		String res = "Para encontrar o total, somamos as duas quantidades: \\(\\\\\\)";
		res += "\\(" + a + " + " + b + " = \\mathbf{" + soma + "}\\)";
		return res;
	}
}
