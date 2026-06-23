package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.resolucaonatural.ResolucaoNatural;

public class TresParcelas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 100 + rand.nextInt(300);
		int b = 100 + rand.nextInt(300);
		int c = 50 + rand.nextInt(200);
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
