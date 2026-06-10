package matematica.basico.racionais.nivel2package;

import java.util.Arrays;

import matematica.GeradorExercicio;

public class Racionais4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(8);  // 1..8
		int b = 2 + rand.nextInt(8);  // 2..9
		int c = 1 + rand.nextInt(8);  // 1..8
		int d = 2 + rand.nextInt(8);  // 2..9
		while(b == d) d = 2 + rand.nextInt(8);

		// Garante que as frações não sejam iguais (produto cruzado diferente)
		long cross1 = (long) a * d;
		long cross2 = (long) c * b;
		while(cross1 == cross2)
		{
			c = 1 + rand.nextInt(8);
			cross2 = (long) c * b;
		}

		String f1 = "\\dfrac{" + a + "}{" + b + "}";
		String f2 = "\\dfrac{" + c + "}{" + d + "}";

		addParagrafo("Qual das frações é maior?");
		addParagrafo("\\(" + f1 + "\\quad\\) e \\(\\quad" + f2 + "\\)");

		boolean f1EhMaior = cross1 > cross2;
		String correta   = f1EhMaior ? "\\(" + f1 + "\\)" : "\\(" + f2 + "\\)";
		String incorreta = f1EhMaior ? "\\(" + f2 + "\\)" : "\\(" + f1 + "\\)";
		embaralharEAdicionarAlternativas(correta,
			Arrays.asList(incorreta, "São iguais", "Não é possível determinar"));

		String fracMaior = f1EhMaior ? f1 : f2;
		String res = "Usamos o produto cruzado: multiplicamos o numerador de cada fração "
				   + "pelo denominador da outra. \\(\\\\\\)";
		res += "\\(" + a + " \\times " + d + " = " + cross1 + "\\) e \\("
			 + c + " \\times " + b + " = " + cross2 + "\\). \\(\\\\\\)";
		res += "Como \\(" + cross1 + (f1EhMaior ? " > " : " < ") + cross2
			 + "\\), a maior fração é \\(" + fracMaior + "\\).";
		setResolucao(res);
	}
}
