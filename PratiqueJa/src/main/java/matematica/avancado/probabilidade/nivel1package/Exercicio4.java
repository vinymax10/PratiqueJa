package matematica.avancado.probabilidade.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.Racional;

// Eventos mutuamente exclusivos: P(A∪B) = P(A) + P(B), dado honesto de 6 faces
public class Exercicio4 extends GeradorExercicio
{
	// {texto pergunta, nA, nB, textoA (LaTeX), textoB (LaTeX)}
	private static final Object[][] CENARIOS = {
		{"par ou 5",                 3, 1, "par: \\(\\{2,4,6\\}\\)",   "5: \\(\\{5\\}\\)"},
		{"ímpar ou 6",               3, 1, "ímpar: \\(\\{1,3,5\\}\\)", "6: \\(\\{6\\}\\)"},
		{"múltiplo de 3 ou 5",       2, 1, "múltiplo de 3: \\(\\{3,6\\}\\)", "5: \\(\\{5\\}\\)"},
		{"1 ou par",                 1, 3, "1: \\(\\{1\\}\\)",         "par: \\(\\{2,4,6\\}\\)"},
		{"menor que 3 ou maior que 5", 2, 1, "menor que 3: \\(\\{1,2\\}\\)", "maior que 5: \\(\\{6\\}\\)"},
		{"2 ou 3",                   1, 1, "2: \\(\\{2\\}\\)",         "3: \\(\\{3\\}\\)"},
	};

	@Override
	protected void construir()
	{
		Object[] cen = CENARIOS[rand.nextInt(CENARIOS.length)];
		String descricao = (String) cen[0];
		int nA = (int) cen[1];
		int nB = (int) cen[2];
		String textoA = (String) cen[3];
		String textoB = (String) cen[4];
		int nFav = nA + nB;

		addParagrafo("Um dado honesto de seis faces é lançado uma vez."
				+ " Qual a probabilidade de sair " + descricao + "?");

		Racional pA = new Racional(nA, 6); pA.fatoracao(2);
		Racional pB = new Racional(nB, 6); pB.fatoracao(2);

		Racional res = new Racional(nFav, 6);
		String fraRes = res.showDfrac();
		res.fatoracao(2);
		boolean resSimp = res.isSimplificou();

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + pA.showDfrac() + "\\)");              // só P(A)
		distratores.add("\\(" + pB.showDfrac() + "\\)");              // só P(B)
		Racional d3 = new Racional(nFav + 1, 6); d3.fatoracao(2);
		distratores.add("\\(" + d3.showDfrac() + "\\)");              // contou uma face a mais
		embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", distratores);

		String resolucao = "Como os eventos são mutuamente exclusivos,"
				+ " \\(P(A \\cup B) = P(A) + P(B)\\):\\(\\\\\\)";
		resolucao += "\\(A =\\) " + textoA + "\\(\\\\\\)";
		resolucao += "\\(B =\\) " + textoB + "\\(\\\\\\)";
		resolucao += "\\(n(A)=" + nA + ",\\quad n(B)=" + nB + ",\\quad n(\\Omega)=6 \\\\";
		resolucao += "P(A)=" + pA.showDfrac() + ",\\quad P(B)=" + pB.showDfrac() + " \\\\";
		resolucao += "P(A \\cup B)=" + pA.showDfrac() + "+" + pB.showDfrac() + "=";
		if(resSimp)
			resolucao += fraRes + "=\\mathbf{" + res.showDfrac() + "}\\)";
		else
			resolucao += "\\mathbf{" + fraRes + "}\\)";
		setResolucao(resolucao);
	}
}
