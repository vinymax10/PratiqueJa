package matematica.basico.divisaonatural;

import java.util.Random;

import matematica.Nomes;

// Prova real da divisão exata: a / b = q; confere-se com b x q = a. A resposta é o dividendo a.
// Placeholders: $a (dividendo), $b (divisor), $q (quociente) e $nomeM/$nomeF.
public class ProblemaProvaReal
{
	public String texto;
	public TipoProvaReal tipo;

	public int a, b, q;
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
			case 1:  q = 2 + rand.nextInt(8);   b = 2 + rand.nextInt(8);   break;
			case 2:  q = 5 + rand.nextInt(16);  b = 3 + rand.nextInt(7);   break;
			default: q = 20 + rand.nextInt(80); b = 6 + rand.nextInt(14);  break;
		}
		a = q * b;
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
		pergunta = pergunta.replace("$q", "" + q);
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

	public String resolucao()
	{
		String res = "A prova real da divisão é: divisor \\(\\times\\) quociente \\(+\\) resto \\(=\\) dividendo. \\(\\\\\\)";
		res += "Como o resto é zero: \\(\\\\\\)";
		res += "\\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
		return res;
	}
}
