package matematica.basico.planocartesiano.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.basico.planocartesiano.config.ConfigPlanoCartesiano;

public class Image1 extends GeradorExercicio
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

		if (tipo == 0)
		{
			nomeRef = "eixo \\(x\\)";
			rx = a; ry = -b;
		}
		else if (tipo == 1)
		{
			nomeRef = "eixo \\(y\\)";
			rx = -a; ry = b;
		}
		else
		{
			nomeRef = "origem";
			rx = -a; ry = -b;
		}

		String preposicao = nomeRef.equals("origem") ? "à" : "ao";
		addParagrafo("O ponto P está indicado no plano. Qual é o ponto simétrico de P em relação " + preposicao + " " + nomeRef + "?");
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

		if (tipo == 0)
		{
			addResolucao("A simetria em relação ao eixo \\(x\\) mantém a abscissa e inverte o sinal da ordenada:");
			addResolucao("\\(P(a,\\;b) \\to P'(a,\\;{-b})\\).");
		}
		else if (tipo == 1)
		{
			addResolucao("A simetria em relação ao eixo \\(y\\) inverte o sinal da abscissa e mantém a ordenada:");
			addResolucao("\\(P(a,\\;b) \\to P'({-a},\\;b)\\).");
		}
		else
		{
			addResolucao("A simetria em relação à origem inverte os sinais de ambas as coordenadas (equivale a refletir no eixo \\(x\\) e depois no eixo \\(y\\)):");
			addResolucao("\\(P(a,\\;b) \\to P'({-a},\\;{-b})\\).");
		}
		addResolucao("Do plano, identificamos \\(P = (" + a + ",\\;" + b + ")\\).");
		addResolucao("Aplicando a regra: \\(P' = \\mathbf{(" + rx + ",\\;" + ry + ")}\\).");
	}

	private String par(int x, int y)
	{
		return "\\((" + x + ",\\;" + y + ")\\)";
	}
}
