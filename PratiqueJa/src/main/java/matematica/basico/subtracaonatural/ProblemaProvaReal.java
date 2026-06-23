package matematica.basico.subtracaonatural;

import java.util.Random;

import matematica.Nomes;

// Prova real da subtração: a - b = c; confere-se com c + b = a. A resposta é o minuendo a.
// Placeholders no enunciado: $a (minuendo), $b (subtraendo), $c (diferença), $nomeM/$nomeF.
public class ProblemaProvaReal
{
	public String texto;
	public TipoProvaReal tipo;

	public int a, b, c;
	public String nomeM, nomeF;

	public ProblemaProvaReal(String texto)
	{
		this(texto, TipoProvaReal.PROVA_REAL);
	}

	public ProblemaProvaReal(String texto, TipoProvaReal tipo)
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
			case 1:  b = 5 + rand.nextInt(15);   a = b + 10 + rand.nextInt(30);  break;
			case 2:  b = 10 + rand.nextInt(30);  a = b + 20 + rand.nextInt(60);  break;
			default: b = 50 + rand.nextInt(150); a = b + 100 + rand.nextInt(500); break;
		}
		c = a - b;
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaProvaReal clone()
	{
		return new ProblemaProvaReal(texto, tipo);
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
			"A prova real da subtração usa a operação inversa: se \\(a - b = c\\), então \\(c + b = a\\).",
			"Verificando:",
			"\\(" + c + " + " + b + " = \\mathbf{" + a + "}\\)"
		};
	}
}
