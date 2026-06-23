package matematica.avancado.estatistica.nivel3package;

import java.util.ArrayList;
import java.util.List;
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

	public String[] resolucao()
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

		List<String> passos = new ArrayList<>();
		if(tipo == TipoDispersao.Variancia)
			passos.add("A variância mede a dispersão dos dados: é a média dos quadrados dos desvios em relação à média.");
		else
			passos.add("O desvio padrão é a raiz quadrada da variância e mede o espalhamento dos dados.");
		passos.add("Primeiro, a média dos dados " + AuxEstatistica.listaStr(valores) + ":");
		passos.add("\\(\\bar{x} = \\dfrac{" + AuxEstatistica.somaStr(valores) + "}{" + n + "} = " + media + "\\)");
		passos.add("Desvios \\(d_i = x_i - \\bar{x}\\): " + devs + ".");
		passos.add("\\(" + ParCor.formula("\\sigma^2 = \\dfrac{\\sum (x_i - \\bar{x})^2}{n}") + "\\)");

		if(tipo == TipoDispersao.Variancia)
		{
			passos.add("\\(\\sigma^2 = \\dfrac{" + quad + "}{" + n + "} = \\\\ "
					+ "\\dfrac{" + somaSq + "}{" + n + "} = \\mathbf{" + var + "}\\)");
		}
		else
		{
			passos.add("\\(\\sigma^2 = \\dfrac{" + quad + "}{" + n + "} = \\\\ "
					+ "\\dfrac{" + somaSq + "}{" + n + "} = " + var + "\\)");
			passos.add("Extraindo a raiz quadrada:");
			passos.add("\\(\\sigma = \\sqrt{" + var + "} = \\mathbf{" + sigma + "}\\)");
		}

		return passos.toArray(new String[0]);
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
