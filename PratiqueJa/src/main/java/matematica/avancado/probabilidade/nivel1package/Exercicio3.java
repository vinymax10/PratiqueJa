package matematica.avancado.probabilidade.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.Racional;

// Evento complementar: P(A^c) = 1 - P(A)
public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(9);   // 2–10 bolas da cor pedida
		int b = 3 + rand.nextInt(12);  // 3–14 bolas da outra cor
		int total = a + b;

		String[][][] cenarios = {
			{{"vermelha","vermelhas"},{"azul","azuis"}},
			{{"branca","brancas"},{"preta","pretas"}},
			{{"amarela","amarelas"},{"verde","verdes"}},
			{{"laranja","laranjas"},{"cinza","cinzas"}},
			{{"rosa","rosas"},{"marrom","marrons"}},
		};
		String[][] c = cenarios[rand.nextInt(cenarios.length)];
		String sa = a == 1 ? c[0][0] : c[0][1];
		String sb = b == 1 ? c[1][0] : c[1][1];
		String corA = c[0][0];

		addParagrafo("Uma urna contém " + a + " bola" + (a > 1 ? "s" : "") + " " + sa
				+ " e " + b + " bola" + (b > 1 ? "s" : "") + " " + sb
				+ ". Retira-se uma bola ao acaso."
				+ " Qual a probabilidade de a bola retirada não ser " + corA + "?");

		Racional pA = new Racional(a, total);
		String fraPA = pA.showDfrac();
		pA.fatoracao(2);
		boolean pASimp = pA.isSimplificou();

		Racional pAc = new Racional(b, total);
		String fraAc = pAc.showDfrac();
		pAc.fatoracao(2);
		boolean pAcSimp = pAc.isSimplificou();

		// Quando a==b, P(A)==P(A^c): substituir d1 para evitar duplicação
		Racional d1 = pA;
		if (pA.showDfrac().equals(pAc.showDfrac()))
		{
			d1 = new Racional(a + 1, total);
			d1.fatoracao(2);
		}
		Racional d2 = new Racional(b - 1, total); d2.fatoracao(2);
		// Quando a==b-1: d1=pA=a/total==(b-1)/total=d2; usar (b-2)/total para d2
		if (d2.showDfrac().equals(d1.showDfrac()))
		{
			d2 = new Racional(b - 2, total); d2.fatoracao(2);
		}
		Racional d3 = new Racional(b, total + 1); d3.fatoracao(2);
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1.showDfrac() + "\\)");               // confundiu P(A) com P(A^c)
		distratores.add("\\(" + d2.showDfrac() + "\\)");               // n(A^c) - 1
		distratores.add("\\(" + d3.showDfrac() + "\\)");               // n(Ω) inflado
		embaralharEAdicionarAlternativas("\\(" + pAc.showDfrac() + "\\)", distratores);

		addResolucao("Usando \\(P(A^c) = 1 - P(A)\\):");
		addResolucao("\\(A =\\) tirar bola " + corA);
		addResolucao("\\(n(A)=" + a + ",\\quad n(\\Omega)=" + total + "\\)");
		addResolucao("\\(P(A)=" + fraPA + (pASimp ? "=" + pA.showDfrac() : "") + "\\)");
		// "1 - P(A)" usa a forma simplificada de P(A)
		String res = "\\(P(A^c)=1-" + pA.showDfrac()
				+ "=\\dfrac{" + total + "-" + a + "}{" + total + "}=";
		if(pAcSimp)
			res += fraAc + "=\\mathbf{" + pAc.showDfrac() + "}\\)";
		else
			res += "\\mathbf{" + fraAc + "}\\)";
		addResolucao(res);
	}
}
