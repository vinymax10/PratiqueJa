package matematica.avancado.probabilidade.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.Racional;

// Combinatória: retirada simultânea de 2 bolas de urna com 2 cores
// P(mesma cor) = [C(a,2) + C(b,2)] / C(total,2)
// P(cores diferentes) = a·b / C(total,2)
public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Pares (a,b) com C(total,2) não muito grande e resultados simplificáveis
		int[][] pares = {{4,3},{5,3},{4,4},{5,4},{6,4},{3,3},{6,3},{5,5}};
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

		// C(n,2) = n(n-1)/2
		int cA2 = a * (a - 1) / 2;
		int cB2 = b * (b - 1) / 2;
		int cT2 = total * (total - 1) / 2;

		boolean perguntaMesmaCor = rand.nextBoolean();

		if(perguntaMesmaCor)
		{
			// P(2 bolas da mesma cor) = (C(a,2) + C(b,2)) / C(total,2)
			int numFav = cA2 + cB2;
			addParagrafo("Uma urna contém " + a + " bolas " + corAs + " e " + b + " bolas " + corBs
					+ ". Retiram-se 2 bolas simultaneamente."
					+ " Qual a probabilidade de as duas bolas serem da mesma cor?");

			Racional res = new Racional(numFav, cT2);
			String fraRes = res.showDfrac(); res.fatoracao(2); boolean resSimp = res.isSimplificou();

			Racional dCorDif = new Racional(a * b, cT2); dCorDif.fatoracao(2);
			Racional dSoA   = new Racional(cA2, cT2);   dSoA.fatoracao(2);
			Racional dSoB   = new Racional(cB2, cT2);   dSoB.fatoracao(2);
			List<String> dis = new ArrayList<>();
			dis.add("\\(" + dCorDif.showDfrac() + "\\)");  // P(cores diferentes): complementar
			dis.add("\\(" + dSoA.showDfrac() + "\\)");     // só C(a,2)/C(total,2)
			dis.add("\\(" + dSoB.showDfrac() + "\\)");     // só C(b,2)/C(total,2)
			embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", dis);

			addResolucao("Usando combinações para contar os casos:");
			addResolucao("\\(A =\\) duas bolas da mesma cor");
			addResolucao("\\(n(\\Omega) = C_{" + total + ",2} = \\dfrac{" + total + " \\cdot " + (total-1) + "}{2} = " + cT2 + "\\)");
			addResolucao("\\(n(A) = C_{" + a + ",2} + C_{" + b + ",2} = "
					+ "\\dfrac{" + a + " \\cdot " + (a-1) + "}{2} + \\dfrac{" + b + " \\cdot " + (b-1) + "}{2} = "
					+ cA2 + "+" + cB2 + "=" + numFav + "\\)");
			if(resSimp)
				addResolucao("\\(P(A)=" + fraRes + "=\\mathbf{" + res.showDfrac() + "}\\)");
			else
				addResolucao("\\(P(A)=\\mathbf{" + fraRes + "}\\)");
		}
		else
		{
			// P(cores diferentes) = a·b / C(total,2)
			int numFav = a * b;
			addParagrafo("Uma urna contém " + a + " bolas " + corAs + " e " + b + " bolas " + corBs
					+ ". Retiram-se 2 bolas simultaneamente."
					+ " Qual a probabilidade de as duas bolas serem de cores diferentes?");

			Racional res = new Racional(numFav, cT2);
			String fraRes = res.showDfrac(); res.fatoracao(2); boolean resSimp = res.isSimplificou();

			int numMesmaCor = cA2 + cB2;
			Racional dMesma = new Racional(numMesmaCor, cT2); dMesma.fatoracao(2);
			Racional dSoA   = new Racional(cA2, cT2);         dSoA.fatoracao(2);
			Racional dSoB   = new Racional(cB2, cT2);         dSoB.fatoracao(2);
			List<String> dis = new ArrayList<>();
			dis.add("\\(" + dMesma.showDfrac() + "\\)");   // P(mesma cor): trocou o evento
			dis.add("\\(" + dSoA.showDfrac() + "\\)");     // só C(a,2)/C(total,2)
			dis.add("\\(" + dSoB.showDfrac() + "\\)");     // só C(b,2)/C(total,2)
			embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", dis);

			addResolucao("Usando combinações para contar os casos:");
			addResolucao("\\(A =\\) duas bolas de cores diferentes");
			addResolucao("\\(n(\\Omega) = C_{" + total + ",2} = \\dfrac{" + total + " \\cdot " + (total-1) + "}{2} = " + cT2 + "\\)");
			addResolucao("\\(n(A) = C_{" + a + ",1} \\cdot C_{" + b + ",1} = " + a + " \\cdot " + b + " = " + numFav + "\\)");
			if(resSimp)
				addResolucao("\\(P(A)=" + fraRes + "=\\mathbf{" + res.showDfrac() + "}\\)");
			else
				addResolucao("\\(P(A)=\\mathbf{" + fraRes + "}\\)");
		}
	}
}
