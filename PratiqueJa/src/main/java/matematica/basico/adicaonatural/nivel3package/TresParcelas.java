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

		String res = "Somamos as duas primeiras parcelas: \\(\\\\\\)";
		res += "\\(" + ResolucaoNatural.soma(a, b, true) + "\\) \\(\\\\\\)";
		res += "Somamos o resultado \\(" + ab + "\\) com a terceira parcela: \\(\\\\\\)";
		res += "\\(" + ResolucaoNatural.soma(ab, c, true) + "\\)";
		setResolucao(res);
	}
}
