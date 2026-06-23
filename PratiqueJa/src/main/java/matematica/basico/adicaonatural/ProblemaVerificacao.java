package matematica.basico.adicaonatural;

import java.util.Random;

import matematica.Nomes;

// Verificação da adição: a + b = c; confere-se com c - b = a. A resposta é a parcela a.
// Placeholders: $a, $b (parcelas), $c (soma) e $nomeM/$nomeF.
public class ProblemaVerificacao
{
	public String texto;
	public TipoVerificacao tipo;

	public int a, b, c;
	public String nomeM, nomeF;

	public ProblemaVerificacao(String texto)
	{
		this(texto, TipoVerificacao.INVERSA);
	}

	public ProblemaVerificacao(String texto, TipoVerificacao tipo)
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
			case 1:  a = 10 + rand.nextInt(30);   b = 10 + rand.nextInt(30);   break;
			case 2:  a = 20 + rand.nextInt(60);   b = 20 + rand.nextInt(60);   break;
			default: a = 100 + rand.nextInt(400); b = 100 + rand.nextInt(400); break;
		}
		c = a + b;
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaVerificacao clone()
	{
		return new ProblemaVerificacao(texto, tipo);
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
		return a;
	}

	public String[] resolucao()
	{
		return new String[]
		{
			"A verificação da adição usa a operação inversa: se \\(a + b = c\\), então \\(c - b = a\\).",
			"\\(" + c + " - " + b + " = \\mathbf{" + a + "}\\)"
		};
	}
}
