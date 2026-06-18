package matematica.avancado.probabilidade.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.Racional;

// Teorema de Bayes: P(A|B) = P(B|A)·P(A) / [P(B|A)·P(A) + P(B|A^c)·P(A^c)]
// Cenário: teste diagnóstico com sensibilidade e especificidade conhecidas.
// Valores escolhidos para que todos os números sejam inteiros (trabalhar em /1000 ou /100).
public class Exercicio9 extends GeradorExercicio
{
	// Cada cenário: {nome doença, sensibilidade%, especificidade%, prevalência%}
	// Prevalência, sensibilidade e especificidade são múltiplos de 10 para facilitar contas.
	// Trabalhamos com população de 1000 para tornar os números inteiros.
	private static final int[][] CENARIOS = {
		// {prev%, sens%, espec%}  — prev * 10 = doentes em 1000
		{10, 90, 80},   // 100 doentes: 90 VP, 10 FN; 900 saudáveis: 180 FP, 720 VN
		{20, 80, 90},   // 200 doentes: 160 VP; 800 saudáveis: 80 FP
		{10, 80, 90},   // 100 doentes: 80 VP; 900 saudáveis: 90 FP
		{20, 90, 70},   // 200 doentes: 180 VP; 800 saudáveis: 240 FP
	};

	private static final String[] DOENCAS = {
		"uma certa doença",
		"determinada infecção",
		"certa condição clínica",
	};

	@Override
	protected void construir()
	{
		int[] cen = CENARIOS[rand.nextInt(CENARIOS.length)];
		int prev = cen[0];    // prevalência em %
		int sens = cen[1];    // sensibilidade em %
		int espec = cen[2];   // especificidade em %

		String doenca = DOENCAS[rand.nextInt(DOENCAS.length)];

		// Em uma população de 1000 pessoas:
		int doentes    = prev * 10;          // prev% de 1000
		int saudaveis  = 1000 - doentes;
		int vp         = doentes * sens / 100;     // verdadeiros positivos
		int fp         = saudaveis * (100 - espec) / 100;  // falsos positivos
		int totalPos   = vp + fp;

		// P(doente | positivo) = VP / (VP + FP)
		Racional res = new Racional(vp, totalPos);
		String fraRes = res.showDfrac();
		res.fatoracao(2);
		boolean resSimp = res.isSimplificou();

		addParagrafo("Um teste para " + doenca + " tem sensibilidade de " + sens
				+ "% (detecta corretamente " + sens + "% dos doentes)"
				+ " e especificidade de " + espec
				+ "% (identifica corretamente " + espec + "% dos saudáveis)."
				+ " A prevalência da doença na população é de " + prev + "%."
				+ " Se uma pessoa testa positivo, qual a probabilidade de ela realmente ter " + doenca + "?");

		// Distratores
		// d1: confunde sensibilidade com VPP
		Racional dSens = new Racional(sens, 100); dSens.fatoracao(2);
		// d2: VP/doentes (repete sensibilidade em fração própria)
		Racional dFP   = new Racional(fp, totalPos); dFP.fatoracao(2);
		// d3: prevalência
		Racional dPrev = new Racional(prev, 100); dPrev.fatoracao(2);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + dSens.showDfrac() + "\\)");   // confundiu com sensibilidade
		distratores.add("\\(" + dFP.showDfrac() + "\\)");     // P(FP|positivo): complementar
		distratores.add("\\(" + dPrev.showDfrac() + "\\)");   // confundiu com prevalência
		embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", distratores);

		String resolucao = "Usando o Teorema de Bayes com uma população de 1000 pessoas:\\(\\\\\\)";
		resolucao += "\\(A =\\) pessoa tem " + doenca + "\\(\\\\\\)";
		resolucao += "\\(B =\\) teste dá positivo\\(\\\\\\)";
		resolucao += "Em 1000 pessoas: " + doentes + " doentes, " + saudaveis + " saudáveis.\\(\\\\\\)";
		resolucao += "Verdadeiros positivos: \\(" + sens + "\\%\\) de " + doentes
				+ " \\(= " + vp + "\\)\\(\\\\\\)";
		resolucao += "Falsos positivos: \\(" + (100 - espec) + "\\%\\) de " + saudaveis
				+ " \\(= " + fp + "\\)\\(\\\\\\)";
		resolucao += "Total de positivos: \\(" + vp + " + " + fp + " = " + totalPos + "\\)\\(\\\\\\)";
		resolucao += "\\(P(A|B) = \\dfrac{" + vp + "}{" + totalPos + "}";
		if (resSimp)
			resolucao += " = \\mathbf{" + res.showDfrac() + "}\\)";
		else
			resolucao += " = \\mathbf{" + fraRes + "}\\)";
		setResolucao(resolucao);
	}
}
