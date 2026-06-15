package matematica.avancado.estatistica.nivel1package;

import java.util.Random;

import matematica.ParCor;
import matematica.avancado.estatistica.AuxEstatistica;

public class ProblemaMediaSimples
{
	public String texto;

	public int[] valores;

	public ProblemaMediaSimples(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		int n = 5;
		// gera valores cuja soma é múltipla de n (média inteira)
		do
		{
			valores = new int[n];
			for(int i = 0; i < n; i++)
				valores[i] = 2 + rand.nextInt(18);
		}
		while(AuxEstatistica.somar(valores) % n != 0);
	}

	public String resolucao()
	{
		int n = valores.length;
		int total = AuxEstatistica.somar(valores);
		int media = total / n;

		String res = "A média aritmética simples é a soma de todos os valores dividida pela quantidade de elementos:";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("\\bar{x} = \\dfrac{x_1 + x_2 + \\cdots + x_n}{n}") + "\\)";
		res += "\\(\\\\\\)";
		res += "Somando os " + n + " valores e dividindo por \\(n = " + n + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(\\bar{x} = \\dfrac{" + AuxEstatistica.somaStr(valores) + "}{" + n + "} = \\\\ ";
		res += "\\dfrac{" + total + "}{" + n + "} = \\mathbf{" + media + "}\\)";

		return res;
	}

	public String resultado()
	{
		return "" + (AuxEstatistica.somar(valores) / valores.length);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$v"))
			pergunta = pergunta.replace("$v", AuxEstatistica.listaStr(valores));

		return pergunta;
	}

	public ProblemaMediaSimples clone()
	{
		return new ProblemaMediaSimples(texto);
	}
}
