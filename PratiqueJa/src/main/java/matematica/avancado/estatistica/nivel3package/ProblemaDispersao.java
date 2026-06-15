package matematica.avancado.estatistica.nivel3package;

import java.util.Random;

import matematica.ParCor;
import matematica.avancado.estatistica.AuxEstatistica;

public class ProblemaDispersao
{
	public String texto;

	public int[] valores;   // 5 valores, ordenados
	public int media;
	public TipoDispersao tipo;

	public ProblemaDispersao(String texto, TipoDispersao tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		int k = 1 + rand.nextInt(3);     // 1, 2 ou 3
		media = 12 + rand.nextInt(9);    // média inteira 12..20
		// desvios {-3k, -k, 0, k, 3k} → variância 4k², desvio padrão 2k
		valores = new int[]{ media - 3*k, media - k, media, media + k, media + 3*k };
	}

	private int variancia()
	{
		int somaSq = 0;
		for(int x : valores) somaSq += (x - media) * (x - media);
		return somaSq / valores.length;
	}

	public String resolucao()
	{
		int n = valores.length;
		int var = variancia();
		int sigma = (int) Math.round(Math.sqrt(var));

		StringBuilder devs = new StringBuilder();
		StringBuilder quad = new StringBuilder();
		int somaSq = 0;
		for(int i = 0; i < n; i++)
		{
			int d = valores[i] - media;
			devs.append(d);
			quad.append(d < 0 ? "\\left(" + d + "\\right)^2" : d + "^2");
			somaSq += d * d;
			if(i < n - 1)
			{
				devs.append(", ");
				quad.append(" + ");
			}
		}

		String res;
		if(tipo == TipoDispersao.Variancia)
			res = "A variância mede a dispersão dos dados: é a média dos quadrados dos desvios em relação à média.";
		else
			res = "O desvio padrão é a raiz quadrada da variância e mede o espalhamento dos dados.";
		res += "\\(\\\\\\)";
		res += "Primeiro, a média dos dados " + AuxEstatistica.listaStr(valores) + ":";
		res += "\\(\\\\\\)";
		res += "\\(\\bar{x} = \\dfrac{" + AuxEstatistica.somaStr(valores) + "}{" + n + "} = " + media + "\\)";
		res += "\\(\\\\\\)";
		res += "Desvios \\(d_i = x_i - \\bar{x}\\): " + devs + ".";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("\\sigma^2 = \\dfrac{\\sum (x_i - \\bar{x})^2}{n}") + "\\)";
		res += "\\(\\\\\\)";

		if(tipo == TipoDispersao.Variancia)
		{
			res += "\\(\\sigma^2 = \\dfrac{" + quad + "}{" + n + "} = \\\\ ";
			res += "\\dfrac{" + somaSq + "}{" + n + "} = \\mathbf{" + var + "}\\)";
		}
		else
		{
			res += "\\(\\sigma^2 = \\dfrac{" + quad + "}{" + n + "} = \\\\ ";
			res += "\\dfrac{" + somaSq + "}{" + n + "} = " + var + "\\)";
			res += "\\(\\\\\\)";
			res += "Extraindo a raiz quadrada:";
			res += "\\(\\\\\\)";
			res += "\\(\\sigma = \\sqrt{" + var + "} = \\mathbf{" + sigma + "}\\)";
		}

		return res;
	}

	public String resultado()
	{
		int var = variancia();
		if(tipo == TipoDispersao.Variancia)
			return "" + var;
		return "" + (int) Math.round(Math.sqrt(var));
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$v"))
			pergunta = pergunta.replace("$v", AuxEstatistica.listaStr(valores));

		return pergunta;
	}

	public ProblemaDispersao clone()
	{
		return new ProblemaDispersao(texto,tipo);
	}
}
