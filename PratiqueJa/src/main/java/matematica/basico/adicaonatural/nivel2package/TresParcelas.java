package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.resolucaonatural.ResolucaoNatural;

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

		String res = "Somamos as duas primeiras parcelas: \\(\\\\\\)";
		res += "\\(" + ResolucaoNatural.soma(a, b, true) + "\\) \\(\\\\\\)";
		res += "Somamos o resultado \\(" + ab + "\\) com a terceira parcela: \\(\\\\\\)";
		res += "\\(" + ResolucaoNatural.soma(ab, c, true) + "\\)";
		setResolucao(res);
	}
}
