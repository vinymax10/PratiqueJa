package matematica.avancado.estatistica.nivel2package;

import java.util.Random;

import matematica.avancado.estatistica.AuxEstatistica;

public class ProblemaMediana
{
	public String texto;

	public int[] valores;

	public ProblemaMediana(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		int n = rand.nextBoolean() ? 5 : 7;
		valores = new int[n];
		for(int i = 0; i < n; i++)
			valores[i] = 2 + rand.nextInt(28);
	}

	public String resolucao()
	{
		int n = valores.length;
		int[] ord = AuxEstatistica.ordenar(valores);
		int pos = (n + 1) / 2;
		int mediana = ord[pos - 1];

		String res = "A mediana (Me) é o valor central dos dados dispostos em ordem crescente (rol).";
		res += "\\(\\\\\\)";
		res += "Ordenando o conjunto: " + AuxEstatistica.listaStr(ord) + ".";
		res += "\\(\\\\\\)";
		res += "Como \\(n = " + n + "\\) é ímpar, a mediana está na posição \\(\\dfrac{n+1}{2} = " + pos + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(\\text{Me} = \\mathbf{" + mediana + "}\\)";

		return res;
	}

	public String resultado()
	{
		int[] ord = AuxEstatistica.ordenar(valores);
		return "" + ord[(ord.length + 1) / 2 - 1];
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$v"))
			pergunta = pergunta.replace("$v", AuxEstatistica.listaStr(valores));

		return pergunta;
	}

	public ProblemaMediana clone()
	{
		return new ProblemaMediana(texto);
	}
}
