package matematica.avancado.probabilidade.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.Racional;

// Combinatória: retirada simultânea de 3 bolas de urna com 2 cores
// P(3 da cor A)      = C(a,3) / C(total,3)
// P(2 da cor A, 1 B) = C(a,2)·C(b,1) / C(total,3)
public class Exercicio18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Pares (a,b) com a≥3 e total≥6, C(total,3) razoável
		int[][] pares = {{5,3},{6,3},{5,4},{6,4},{7,3},{4,3},{5,5},{6,5}};
		int[] par = pares[rand.nextInt(pares.length)];
		int a = par[0], b = par[1], total = a + b;

		String[][][] cores = {
			{{"branca","brancas"},{"preta","pretas"}},
			{{"vermelha","vermelhas"},{"azul","azuis"}},
			{{"amarela","amarelas"},{"verde","verdes"}},
			{{"laranja","laranjas"},{"cinza","cinzas"}},
		};
		String[][] c = cores[rand.nextInt(cores.length)];
		String corA = c[0][0], corAs = c[0][1];
		String corB = c[1][0], corBs = c[1][1];

		// C(n,3) = n(n-1)(n-2)/6
		int cT3 = total * (total - 1) * (total - 2) / 6;
		int cA3 = a * (a - 1) * (a - 2) / 6;
		int cA2 = a * (a - 1) / 2;
		int cB1 = b;
		int num2A1B = cA2 * cB1;

		boolean perg3A = rand.nextBoolean();  // pergunta "3 da cor A" ou "2 da A e 1 da B"

		if(perg3A)
		{
			addParagrafo("Uma urna contém " + a + " bolas " + corAs + " e " + b + " bolas " + corBs
					+ ". Retiram-se 3 bolas simultaneamente."
					+ " Qual a probabilidade de as três bolas serem " + corAs + "?");

			Racional res = new Racional(cA3, cT3);
			String fraRes = res.showDfrac(); res.fatoracao(2); boolean resSimp = res.isSimplificou();

			Racional d1 = new Racional(num2A1B, cT3); d1.fatoracao(2);
			Racional d2 = new Racional(cA2, cT3);     d2.fatoracao(2);
			Racional d3 = new Racional(cA3 + 1, cT3); d3.fatoracao(2);
			List<String> dis = new ArrayList<>();
			dis.add("\\(" + d1.showDfrac() + "\\)");    // P(2A+1B): confundiu evento
			dis.add("\\(" + d2.showDfrac() + "\\)");    // usou C(a,2) em vez de C(a,3)
			dis.add("\\(" + d3.showDfrac() + "\\)");    // off-by-one
			embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", dis);

			String resolucao = "Usando combinações para contar os casos:\\(\\\\\\)";
			resolucao += "\\(A =\\) três bolas " + corAs + "\\(\\\\\\)";
			resolucao += "\\(n(\\Omega) = C_{" + total + ",3} = \\dfrac{" + total + " \\cdot " + (total-1)
					+ " \\cdot " + (total-2) + "}{6} = " + cT3 + " \\\\";
			resolucao += "n(A) = C_{" + a + ",3} = \\dfrac{" + a + " \\cdot " + (a-1)
					+ " \\cdot " + (a-2) + "}{6} = " + cA3 + " \\\\";
			if(resSimp)
				resolucao += "P(A)=" + fraRes + "=\\mathbf{" + res.showDfrac() + "}\\)";
			else
				resolucao += "P(A)=\\mathbf{" + fraRes + "}\\)";
			setResolucao(resolucao);
		}
		else
		{
			addParagrafo("Uma urna contém " + a + " bolas " + corAs + " e " + b + " bolas " + corBs
					+ ". Retiram-se 3 bolas simultaneamente."
					+ " Qual a probabilidade de exatamente 2 bolas serem " + corAs + " e 1 ser " + corB + "?");

			Racional res = new Racional(num2A1B, cT3);
			String fraRes = res.showDfrac(); res.fatoracao(2); boolean resSimp = res.isSimplificou();

			Racional d1 = new Racional(cA3, cT3);         d1.fatoracao(2);
			Racional d2 = new Racional(cA2, cT3);         d2.fatoracao(2);
			Racional d3 = new Racional(cA2 + cB1, cT3);  d3.fatoracao(2);
			List<String> dis = new ArrayList<>();
			dis.add("\\(" + d1.showDfrac() + "\\)");    // P(3A): confundiu evento
			dis.add("\\(" + d2.showDfrac() + "\\)");    // esqueceu multiplicar por C(b,1)
			dis.add("\\(" + d3.showDfrac() + "\\)");    // somou em vez de multiplicar
			embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", dis);

			String resolucao = "Usando combinações para contar os casos:\\(\\\\\\)";
			resolucao += "\\(A =\\) 2 bolas " + corAs + " e 1 bola " + corB + "\\(\\\\\\)";
			resolucao += "\\(n(\\Omega) = C_{" + total + ",3} = \\dfrac{" + total + " \\cdot " + (total-1)
					+ " \\cdot " + (total-2) + "}{6} = " + cT3 + " \\\\";
			resolucao += "n(A) = C_{" + a + ",2} \\cdot C_{" + b + ",1} = "
					+ cA2 + " \\cdot " + cB1 + " = " + num2A1B + " \\\\";
			if(resSimp)
				resolucao += "P(A)=" + fraRes + "=\\mathbf{" + res.showDfrac() + "}\\)";
			else
				resolucao += "P(A)=\\mathbf{" + fraRes + "}\\)";
			setResolucao(resolucao);
		}
	}
}
