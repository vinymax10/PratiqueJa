package matematica.basico.divisaonatural;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import matematica.Nomes;

// Divisão com resto: total $a agrupado em grupos de $b; q = a div b grupos completos,
// r = a mod b sobram. A resposta é textual ("q grupos completos, sobram r") e os
// distratores são gerados aqui (o gerador chama embaralharEAdicionarAlternativas).
public class ProblemaDivisaoResto
{
	public String texto;
	public TipoDivisaoResto tipo;

	public int a, b, q, r;
	public String nomeM, nomeF;

	public ProblemaDivisaoResto(String texto)
	{
		this(texto, TipoDivisaoResto.COM_RESTO);
	}

	public ProblemaDivisaoResto(String texto, TipoDivisaoResto tipo)
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
			case 2:  b = 3 + rand.nextInt(7);   q = 10 + rand.nextInt(20);   break;
			default: b = 12 + rand.nextInt(28); q = 100 + rand.nextInt(150); break;
		}
		r = rand.nextInt(b);
		a = b * q + r;
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaDivisaoResto clone()
	{
		return new ProblemaDivisaoResto(texto, tipo);
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

	private String fmt(int qq, int rr)
	{
		return qq + " grupos completos" + (rr <= 0 ? ", sem sobras" : ", sobram " + rr);
	}

	// resposta correta (textual)
	public String resultado()
	{
		return fmt(q, r);
	}

	public List<String> getDistratores()
	{
		List<String> d = new ArrayList<>();
		d.add(fmt(q + 1, r));
		d.add(fmt(q, r + 1));
		d.add(fmt(q - 1, r + b));
		return d;
	}

	public String[] resolucao()
	{
		if(r == 0)
		{
			return new String[]
			{
				"Dividimos o total pelo tamanho de cada grupo:",
				"\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) sem sobras.",
				"Prova real: \\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)"
			};
		}
		return new String[]
		{
			"Dividimos o total pelo tamanho de cada grupo:",
			"\\(" + a + " \\div " + b + " = " + q + "\\) com resto \\(" + r + "\\).",
			"Portanto, há \\(\\mathbf{" + q + "}\\) grupos completos e sobram \\(\\mathbf{" + r + "}\\).",
			"Prova real: \\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)"
		};
	}
}
