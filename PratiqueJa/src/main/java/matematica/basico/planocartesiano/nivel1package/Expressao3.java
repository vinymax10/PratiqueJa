package matematica.basico.planocartesiano.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(6);
		int b = 1 + rand.nextInt(6);
		while (b == a) b = 1 + rand.nextInt(6);
		if (rand.nextBoolean()) a = -a;
		if (rand.nextBoolean()) b = -b;

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
		addParagrafo("Qual é o ponto simétrico de \\( P(" + a + ",\\;" + b + ") \\) em relação " + preposicao + " " + nomeRef + "?");

		List<String> alts = new ArrayList<>();
		alts.add(par(a, b));
		alts.add(par(a, -b));
		alts.add(par(-a, b));
		alts.add(par(-a, -b));
		String correta = par(rx, ry);
		alts.remove(correta);
		embaralharEAdicionarAlternativas(correta, alts);

		String res;
		if (tipo == 0)
		{
			res = "A simetria em relação ao eixo \\(x\\) mantém a abscissa e inverte o sinal da ordenada: \\(\\\\\\)";
			res += "\\(P(a,\\;b) \\to P'(a,\\;{-b})\\\\ \\) ";
		}
		else if (tipo == 1)
		{
			res = "A simetria em relação ao eixo \\(y\\) inverte o sinal da abscissa e mantém a ordenada: \\(\\\\\\)";
			res += "\\(P(a,\\;b) \\to P'({-a},\\;b)\\\\ \\) ";
		}
		else
		{
			res = "A simetria em relação à origem inverte os sinais de ambas as coordenadas ";
			res += "(equivale a refletir no eixo \\(x\\) e depois no eixo \\(y\\)): \\(\\\\\\)";
			res += "\\(P(a,\\;b) \\to P'({-a},\\;{-b})\\\\ \\) ";
		}
		res += "Aplicando ao ponto \\(P(" + a + ",\\;" + b + ")\\): \\(\\\\\\)";
		res += "\\(P' = (" + rx + ",\\;" + ry + ")\\)";

		setResolucao(res);
	}

	private String par(int x, int y)
	{
		return "\\((" + x + ",\\;" + y + ")\\)";
	}
}
