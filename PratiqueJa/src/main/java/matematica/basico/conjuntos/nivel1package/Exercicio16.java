package matematica.basico.conjuntos.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.Conjunto;

public class Exercicio16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int sizeU = 7 + rand.nextInt(4); // 7..10 elementos
		Conjunto u = new Conjunto(sizeU, 30);
		int sizeA = 2 + rand.nextInt(sizeU - 3); // 2..sizeU-2 (garante comp não-vazio)
		Conjunto a = u.subconjunto(sizeA);
		Conjunto comp = u.menos(a);
		int correto = comp.size();

		addParagrafo("Dado o conjunto universo \\(U\\) e o conjunto \\(A \\subseteq U\\), qual é \\(|A^c|\\)?");
		addParagrafo("\\(U = " + u + "\\)");
		addParagrafo("\\(A = " + a + "\\)");
		gerarAlternativasInteiras(correto);

		String res = "O complementar \\(A^c\\) reúne todos os elementos de \\(U\\) que não estão em \\(A\\). \\(\\\\\\)";
		res += "\\(A^c = U - A = " + comp + "\\\\";
		res += "|A^c| = " + correto + "\\)";
		setResolucao(res);
	}
}
