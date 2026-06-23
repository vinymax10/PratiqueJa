package matematica.avancado.probabilidade.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// "Ao menos um" com dado: P(≥1 seis em n lançamentos) = 1 - (5/6)^n
// n=2: 1 - 25/36  = 11/36
// n=3: 1 - 125/216 = 91/216
// n=4: 1 - 625/1296 = 671/1296
public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 2 + rand.nextInt(3);  // 2, 3 ou 4 lançamentos

		// P(não sair 6) = 5/6; P(nenhum 6 em n lançamentos) = 5^n / 6^n
		long num5 = pow(5, n);
		long den6 = pow(6, n);
		long favoravel = den6 - num5;

		// Escolhe aleatoriamente qual face alvo mostrar (1, 2, 3, 4, 5 ou 6)
		int face = 1 + rand.nextInt(6);
		// "não sair face" = 5/6 em cada lançamento (mesma conta)

		addParagrafo("Um dado honesto de seis faces é lançado \\(" + n + "\\) vezes."
				+ " Qual a probabilidade de o número " + face
				+ " aparecer em ao menos um dos lançamentos?");

		String correta = "\\(\\dfrac{" + favoravel + "}{" + den6 + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + num5 + "}{" + den6 + "}\\)");        // P(nenhum): erro de complementar
		distratores.add("\\(\\dfrac{" + n + "}{6}\\)");                       // n/6: multiplicou P × n
		long errFav = den6 - pow(4, n);
		distratores.add("\\(\\dfrac{" + errFav + "}{" + den6 + "}\\)");       // usou (4/6)^n errado
		embaralharEAdicionarAlternativas(correta, distratores);

		String potStr5 = buildFracPot(5, 6, n);
		String pNenhuma = "\\dfrac{" + num5 + "}{" + den6 + "}";

		addResolucao("Usando \\(P(A^c) = 1 - P(A)\\):");
		addResolucao("\\(A =\\) sair " + face + " em ao menos um lançamento");
		addResolucao("Em cada lançamento, P(não sair " + face + ") \\(= \\dfrac{5}{6}\\)");
		addResolucao("\\(P(A^c) = " + potStr5 + " = " + pNenhuma + "\\)");
		addResolucao("\\(P(A) = 1 - " + pNenhuma + " = \\dfrac{" + den6 + "-" + num5 + "}{" + den6 + "} = \\mathbf{\\dfrac{" + favoravel + "}{" + den6 + "}}\\)");
	}

	private static long pow(long base, int exp)
	{
		long r = 1;
		for(int i = 0; i < exp; i++) r *= base;
		return r;
	}

	private static String buildFracPot(int num, int den, int n)
	{
		return "\\left(\\dfrac{" + num + "}{" + den + "}\\right)^" + n;
	}
}
