package matematica.avancado.estatistica.nivel2package;

import java.util.Random;

import matematica.ParCor;

public class ProblemaMediaPonderada
{
	public String texto;

	public int[] valores;
	public int[] pesos;

	public ProblemaMediaPonderada(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		int n = 3;
		// gera valores/pesos cuja média ponderada é inteira
		do
		{
			valores = new int[n];
			pesos = new int[n];
			for(int i = 0; i < n; i++)
			{
				valores[i] = 4 + rand.nextInt(7);
				pesos[i] = 1 + rand.nextInt(5);
			}
		}
		while(somaProdutos() % somaPesos() != 0);
	}

	private int somaProdutos()
	{
		int s = 0;
		for(int i = 0; i < valores.length; i++)
			s += valores[i] * pesos[i];
		return s;
	}

	private int somaPesos()
	{
		int s = 0;
		for(int p : pesos) s += p;
		return s;
	}

	public String[] resolucao()
	{
		int numTotal = somaProdutos();
		int denTotal = somaPesos();
		int media = numTotal / denTotal;

		StringBuilder num = new StringBuilder();
		StringBuilder den = new StringBuilder();
		for(int i = 0; i < valores.length; i++)
		{
			num.append(valores[i]).append(" \\cdot ").append(pesos[i]);
			den.append(pesos[i]);
			if(i < valores.length - 1)
			{
				num.append(" + ");
				den.append(" + ");
			}
		}

		return new String[]
		{
			"A média ponderada leva em conta o peso de cada valor:",
			"\\(" + ParCor.formula("\\bar{x}_p = \\dfrac{x_1 w_1 + x_2 w_2 + \\cdots}{w_1 + w_2 + \\cdots}") + "\\)",
			"Multiplicando cada valor pelo seu peso e dividindo pela soma dos pesos:",
			"\\(\\bar{x}_p = \\dfrac{" + num + "}{" + den + "} = \\\\ "
				+ "\\dfrac{" + numTotal + "}{" + denTotal + "} = \\mathbf{" + media + "}\\)"
		};
	}

	public String resultado()
	{
		return "" + (somaProdutos() / somaPesos());
	}

	public String getPergunta()
	{
		StringBuilder d = new StringBuilder();
		for(int i = 0; i < valores.length; i++)
		{
			d.append(valores[i]).append(" (peso ").append(pesos[i]).append(")");
			if(i < valores.length - 1)
				d.append(", ");
		}

		String pergunta = texto;
		if(pergunta.contains("$d"))
			pergunta = pergunta.replace("$d", d.toString());

		return pergunta;
	}

	public ProblemaMediaPonderada clone()
	{
		return new ProblemaMediaPonderada(texto);
	}
}
