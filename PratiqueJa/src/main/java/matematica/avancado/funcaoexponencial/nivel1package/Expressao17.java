package matematica.avancado.funcaoexponencial.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Propriedade a^0 = 1 para qualquer base válida
		int[] bases = {2, 3, 4, 5, 7, 10};
		int a = bases[rand.nextInt(bases.length)];

		addParagrafo("Para a função \\(f(x) = " + a + "^x\\), qual é o valor de \\(f(0)\\)?");

		List<String> dist = new ArrayList<>();
		dist.add("0");
		dist.add("" + a);
		dist.add("" + (a + 1));
		embaralharEAdicionarAlternativas("1", dist);

		String res = "Aplicando a propriedade \\(a^0 = 1\\) (qualquer base ≠ 0): \\(\\\\\\)";
		res += "\\(f(0) = " + a + "^0 = \\mathbf{1}\\)";

		setResolucao(res);
	}
}
