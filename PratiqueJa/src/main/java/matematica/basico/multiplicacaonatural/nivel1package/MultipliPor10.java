package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;

public class MultipliPor10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] potencias = {10, 100, 1000};
		int pot = potencias[rand.nextInt(potencias.length)];

		int a;
		if (pot == 10)
			a = 11 + rand.nextInt(89);
		else if (pot == 100)
			a = 11 + rand.nextInt(89);
		else
			a = 2 + rand.nextInt(48);

		int resultado = a * pot;

		addParagrafo("Calcule:");
		addParagrafo("\\(" + a + " \\times " + pot + "\\)");

		gerarAlternativasInteiras(resultado);

		int zeros = pot == 10 ? 1 : pot == 100 ? 2 : 3;
		String res = "Multiplicar por \\(" + pot + "\\) equivale a acrescentar "
			+ zeros + " zero" + (zeros > 1 ? "s" : "") + " à direita do número: \\(\\\\\\)";
		res += "\\(" + a + " \\times " + pot + " = \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}
}
