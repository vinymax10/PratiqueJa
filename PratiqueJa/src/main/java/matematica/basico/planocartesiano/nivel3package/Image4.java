package matematica.basico.planocartesiano.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.basico.planocartesiano.config.ConfigPlanoCartesiano;

public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		int b = 1 + rand.nextInt(5);
		while (b == a) b = 1 + rand.nextInt(5);
		if (rand.nextBoolean()) a = -a;
		if (rand.nextBoolean()) b = -b;

		ConfigPlanoCartesiano config = new ConfigPlanoCartesiano(a, b);
		BufferedImage image = config.criarImagem();

		int tipo = rand.nextInt(3);
		String nomeRef;
		int rx, ry;
		String res;

		if (tipo == 0)
		{
			nomeRef = "eixo \\(x\\)";
			rx = a; ry = -b;
			res = "A simetria em relação ao eixo \\(x\\) mantém a abscissa e inverte o sinal da ordenada: \\(\\\\\\)";
			res += "\\(P(a,\\;b) \\to P'(a,\\;{-b})\\). \\(\\\\\\)";
		}
		else if (tipo == 1)
		{
			nomeRef = "eixo \\(y\\)";
			rx = -a; ry = b;
			res = "A simetria em relação ao eixo \\(y\\) inverte o sinal da abscissa e mantém a ordenada: \\(\\\\\\)";
			res += "\\(P(a,\\;b) \\to P'({-a},\\;b)\\). \\(\\\\\\)";
		}
		else
		{
			nomeRef = "origem";
			rx = -a; ry = -b;
			res = "A simetria em relação à origem inverte os sinais de ambas as coordenadas ";
			res += "(equivale a refletir no eixo \\(x\\) e depois no eixo \\(y\\)): \\(\\\\\\)";
			res += "\\(P(a,\\;b) \\to P'({-a},\\;{-b})\\). \\(\\\\\\)";
		}

		addParagrafo("O ponto P está indicado no plano. Qual é o ponto simétrico de P em relação ao " + nomeRef + "?");
		addParagrafoImagem(image);

		String correta = par(rx, ry);
		List<String> alts = new ArrayList<>();
		for (int[] c : new int[][] {{a, b}, {a, -b}, {-a, b}, {-a, -b}})
		{
			String alt = par(c[0], c[1]);
			if (!alt.equals(correta) && !alts.contains(alt))
				alts.add(alt);
		}
		embaralharEAdicionarAlternativas(correta, alts);

		res += "Do plano, identificamos \\(P = (" + a + ",\\;" + b + ")\\). \\(\\\\\\)";
		res += "Aplicando a regra: \\(P' = (" + rx + ",\\;" + ry + ")\\).";
		setResolucao(res);
	}

	private String par(int x, int y)
	{
		return "\\((" + x + ",\\;" + y + ")\\)";
	}
}
