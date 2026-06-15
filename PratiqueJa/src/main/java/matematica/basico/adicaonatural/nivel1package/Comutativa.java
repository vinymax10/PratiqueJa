package matematica.basico.adicaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Comutativa extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(40);
		int b = 10 + rand.nextInt(40);
		int soma = a + b;

		addParagrafo("Sabendo que \\(" + a + " + " + b + " = " + soma + "\\), qual é o valor de \\(" + b + " + " + a + "\\)?");

		gerarAlternativasInteiras(soma);

		String res = "Pela propriedade comutativa, trocar a ordem das parcelas não altera a soma: \\(\\\\\\)";
		res += "\\(" + b + " + " + a + " = " + a + " + " + b + " = \\mathbf{" + soma + "}\\)";
		setResolucao(res);
	}
}
