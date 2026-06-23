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

		addResolucao("Somamos as duas primeiras parcelas:");
		addResolucao("\\(" + a + " + " + b + " = " + ab + "\\)");
		addResolucao("Em seguida, somamos com a terceira parcela:");
		addResolucao("\\(" + ab + " + " + c + " = \\mathbf{" + soma + "}\\)");
	}
}
