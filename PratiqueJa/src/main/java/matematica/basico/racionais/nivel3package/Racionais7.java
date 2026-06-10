package matematica.basico.racionais.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Racionais7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (a/b)^n = a^n / b^n
		int a = 2 + rand.nextInt(5);  // 2..6
		int b = 2 + rand.nextInt(5);  // 2..6
		while(a == b) b = 2 + rand.nextInt(5);
		int n = 2 + rand.nextInt(2);  // expoente 2 ou 3

		long numRes = (long) Math.pow(a, n);
		long denRes = (long) Math.pow(b, n);
		Racional resultado = new Racional(numRes, denRes);
		resultado.fatoracao(2);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\left(\\dfrac{" + a + "}{" + b + "}\\right)^{" + n + "}\\)");
		gerarAlternativas(resultado.toString());

		String res = "Elevamos o numerador e o denominador ao expoente \\(" + n + "\\). \\(\\\\\\)";
		String step = "\\(\\left(\\dfrac{" + a + "}{" + b + "}\\right)^{" + n + "} = \\dfrac{"
					+ a + "^{" + n + "}}{" + b + "^{" + n + "}} = \\dfrac{" + numRes + "}{"
					+ denRes + "}";
		if(resultado.isSimplificou())
			step += " = " + resultado.showDfrac();
		step += "\\)";
		res += step;
		setResolucao(res);
	}
}
