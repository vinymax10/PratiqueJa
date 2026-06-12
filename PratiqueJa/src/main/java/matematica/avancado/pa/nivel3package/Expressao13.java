package matematica.avancado.pa.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 6 + rand.nextInt(10);
		int k = 2;

		Racional a1 = new Racional(1 + rand.nextInt(10), 1 + rand.nextInt(5));
		a1.fatoracao(2);

		Racional r = new Racional(1 + rand.nextInt(5), 1 + rand.nextInt(5));
		r.fatoracao(2);

		Racional an = ResolucaoPA.a(a1, r, n);

		Racional resultado = a1.add(an);
		resultado.fatoracao(2);

		addParagrafo("Em uma PA de " + n + " termos com \\(a_1 = " + a1.showDfrac()
				+ "\\) e \\(a_{" + n + "} = " + an.showDfrac()
				+ "\\), calcule \\(a_2 + a_{" + (n - 1) + "}\\).");
		gerarAlternativas(resultado.toString());

		String res = "Pela propriedade dos termos equidistantes: \\(\\\\\\)";
		res += "\\(a_k + a_{n-k+1} = a_1 + a_n, \\quad \\forall\\; k\\) \\(\\\\\\)";
		res += "Para \\(k = 2\\), temos \\(a_2 + a_{" + (n - 1) + "} = a_1 + a_{" + n + "}\\): \\(\\\\\\)";
		res += "\\(a_2 + a_{" + (n - 1) + "} = " + a1.showDfrac() + " + " + an.showDfrac()
				+ " = \\mathbf{" + resultado.showDfrac() + "}\\)";
		setResolucao(res);
	}
}
