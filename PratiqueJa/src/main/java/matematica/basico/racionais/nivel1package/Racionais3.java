package matematica.basico.racionais.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Racionais3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gera a/b com gcd(a,b) = 1, depois multiplica por k para forçar simplificação
		int a = 2 + rand.nextInt(8);  // 2..9
		int b = 2 + rand.nextInt(8);  // 2..9
		while(a == b) b = 2 + rand.nextInt(8);
		int k = 2 + rand.nextInt(4);  // fator 2..5

		int num = a * k;
		int den = b * k;

		Racional resultado = new Racional(num, den);
		resultado.fatoracao(2);

		addParagrafo("Simplifique a fração:");
		addParagrafo("\\(\\dfrac{" + num + "}{" + den + "}\\)");
		gerarAlternativas(resultado);

		long mdc = gcd(num, den);
		String res = "Dividimos o numerador e o denominador pelo MDC. \\(\\\\\\)";
		res += "\\(\\text{MDC}(" + num + ",\\," + den + ") = " + mdc + "\\). \\(\\\\\\)";
		res += "\\(\\dfrac{" + num + "}{" + den + "} = \\dfrac{"
			 + num + " \\div " + mdc + "}{" + den + " \\div " + mdc
			 + "} = " + resultado.showDfrac() + "\\)";
		setResolucao(res);
	}

	private static long gcd(long a, long b)
	{
		a = Math.abs(a);
		b = Math.abs(b);
		while(b != 0) { long t = b; b = a % b; a = t; }
		return a;
	}
}
