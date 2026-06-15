package matematica.basico.adicaonatural.nivel1package;

import matematica.GeradorExercicio;

public class TresParcelas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 5 + rand.nextInt(15);
		int b = 5 + rand.nextInt(15);
		int c = 5 + rand.nextInt(15);
		int ab = a + b;
		int soma = ab + c;

		addParagrafo("Calcule a soma das três parcelas:");
		addParagrafo("\\(" + a + " + " + b + " + " + c + "\\)");

		gerarAlternativasInteiras(soma);

		String res = "Somamos as duas primeiras parcelas: \\(\\\\\\)";
		res += "\\(" + a + " + " + b + " = " + ab + "\\) \\(\\\\\\)";
		res += "Em seguida, somamos com a terceira parcela: \\(\\\\\\)";
		res += "\\(" + ab + " + " + c + " = \\mathbf{" + soma + "}\\)";
		setResolucao(res);
	}
}
