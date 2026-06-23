package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.resolucaonatural.ResolucaoNatural;

public class TresParcelas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 20 + rand.nextInt(50);
		int b = 20 + rand.nextInt(50);
		int c = 10 + rand.nextInt(40);
		int ab = a + b;
		int soma = ab + c;

		addParagrafo("Calcule a soma das três parcelas:");
		addParagrafo("\\(" + a + " + " + b + " + " + c + "\\)");

		gerarAlternativasInteiras(soma);

		addResolucao("Somamos as duas primeiras parcelas:");
		addResolucao("\\(" + ResolucaoNatural.soma(a, b, true) + "\\)");
		addResolucao("Somamos o resultado \\(" + ab + "\\) com a terceira parcela:");
		addResolucao("\\(" + ResolucaoNatural.soma(ab, c, true) + "\\)");
	}
}
