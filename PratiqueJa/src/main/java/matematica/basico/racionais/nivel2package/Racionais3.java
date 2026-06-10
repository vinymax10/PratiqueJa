package matematica.basico.racionais.nivel2package;

import matematica.GeradorExercicio;

public class Racionais3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a/b = ?/(b*k) — encontrar o numerador faltante = a*k
		int a = 2 + rand.nextInt(9);  // 2..10
		int b = 2 + rand.nextInt(8);  // 2..9
		while(a == b) a = 2 + rand.nextInt(9);
		int k = 2 + rand.nextInt(5);  // fator 2..6

		int denDestino = b * k;
		int numDestino = a * k;

		addParagrafo("Encontre o numerador que completa a fração equivalente:");
		addParagrafo("\\(\\dfrac{" + a + "}{" + b + "} = \\dfrac{\\,?\\,}{" + denDestino + "}\\)");

		gerarAlternativasInteiras(numDestino);

		String res = "Para obter denominador \\(" + denDestino + "\\) a partir de \\(" + b
				   + "\\), multiplicamos por \\(" + k + "\\). \\(\\\\\\)";
		res += "Aplicamos o mesmo fator ao numerador: \\(\\\\\\)";
		res += "\\(\\dfrac{" + a + "}{" + b + "} = \\dfrac{" + a + " \\times " + k + "}{"
			 + b + " \\times " + k + "} = \\dfrac{" + numDestino + "}{" + denDestino + "}\\)";
		setResolucao(res);
	}
}
