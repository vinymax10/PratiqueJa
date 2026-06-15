package matematica.avancado.probabilidade.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.Racional;

// Regra da soma geral (não exclusivos): P(A∪B) = P(A) + P(B) - P(A∩B)
// Cenário: baralho de 52 cartas — P(copas ou figura)
// P(copas) = 13/52, P(figura) = 12/52, P(copas e figura) = 3/52 (J♠Q♠K♠ — wait, copas)
// P(A∪B) = 13/52 + 12/52 - 3/52 = 22/52 = 11/26
public class Exercicio5 extends GeradorExercicio
{
	// Naipes e seus nomes
	private static final String[][] NAIPES = {
		{"copas",    "♥"},
		{"espadas",  "♠"},
		{"ouros",    "♦"},
		{"paus",     "♣"},
	};

	@Override
	protected void construir()
	{
		// Escolhe um naipe aleatório
		String[] naipe = NAIPES[rand.nextInt(NAIPES.length)];
		String nomeNaipe = naipe[0];

		// Figuras: J, Q, K (3 por naipe × 4 naipes = 12)
		// Cartas do naipe escolhido: 13
		// Cartas que são figura do naipe escolhido: 3 (J, Q, K do naipe)
		int nNaipe = 13;
		int nFigura = 12;
		int nIntersec = 3;  // figuras do naipe escolhido
		int total = 52;
		int numFav = nNaipe + nFigura - nIntersec;  // 22

		Racional res = new Racional(numFav, total);
		String fraRes = res.showDfrac();
		res.fatoracao(2);
		boolean resSimp = res.isSimplificou();

		addParagrafo("De um baralho completo de 52 cartas retira-se uma ao acaso."
				+ " Qual a probabilidade de a carta ser de " + nomeNaipe + " ou ser uma figura (J, Q ou K)?");

		Racional pNaipe   = new Racional(nNaipe, total);   pNaipe.fatoracao(2);
		Racional pFigura  = new Racional(nFigura, total);  pFigura.fatoracao(2);
		Racional pUniao   = new Racional(numFav, total);   pUniao.fatoracao(2);

		// Distratores: só naipe, só figura, soma sem subtrair interseção
		Racional dSoNaipe  = new Racional(nNaipe, total);  dSoNaipe.fatoracao(2);
		Racional dSoFig    = new Racional(nFigura, total); dSoFig.fatoracao(2);
		Racional dSemSub   = new Racional(nNaipe + nFigura, total); dSemSub.fatoracao(2);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + dSoNaipe.showDfrac() + "\\)");    // só P(naipe)
		distratores.add("\\(" + dSoFig.showDfrac() + "\\)");      // só P(figura)
		distratores.add("\\(" + dSemSub.showDfrac() + "\\)");     // somou sem subtrair interseção
		embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", distratores);

		String resolucao = "Usando a regra da soma \\(P(A \\cup B) = P(A) + P(B) - P(A \\cap B)\\):\\(\\\\\\)";
		resolucao += "\\(A =\\) carta de " + nomeNaipe + "\\(\\\\\\)";
		resolucao += "\\(B =\\) carta é figura (J, Q ou K)\\(\\\\\\)";
		resolucao += "\\(P(A) = \\dfrac{" + nNaipe + "}{52}, \\quad P(B) = \\dfrac{" + nFigura + "}{52}, \\quad P(A \\cap B) = \\dfrac{" + nIntersec + "}{52} \\\\";
		resolucao += "P(A \\cup B) = \\dfrac{" + nNaipe + "}{52} + \\dfrac{" + nFigura + "}{52} - \\dfrac{" + nIntersec + "}{52} = \\dfrac{" + numFav + "}{52} = \\mathbf{" + res.showDfrac() + "}\\)";
		setResolucao(resolucao);
	}
}
