package matematica.avancado.funcaoquadratica.nivel3package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = (1 + rand.nextInt(3)) * (rand.nextBoolean() ? 1 : -1);
		int b = rand.nextInt(11) - 5;
		int c = rand.nextInt(11) - 5;
		while(b == 0) b = rand.nextInt(11) - 5;

		String funcao = Auxiliar.getNumber(a, "x^2", true)
			+ Auxiliar.getNumber(b, "x", false)
			+ Auxiliar.getNumber(c, "", false);

		boolean pedirSoma = rand.nextBoolean();

		Racional resultado;
		if(pedirSoma)
			resultado = new Racional(-b, a);
		else
			resultado = new Racional(c, a);
		resultado.fatoracao(2);

		String pergunta = pedirSoma
			? "a soma das raízes \\(x_1 + x_2\\)"
			: "o produto das raízes \\(x_1 \\cdot x_2\\)";

		addParagrafo("Usando as relações de Vieta, determine " + pergunta
			+ " de \\(f(x) = " + funcao + "\\)");

		String bDisp = b < 0 ? "(-" + (-b) + ")" : "" + b;

		String res;
		if(pedirSoma)
		{
			res = "Pela relação de Vieta: \\(x_1 + x_2 = \\dfrac{-b}{a}\\\\";
			res += "a = " + a + ", \\quad b = " + b + "\\\\";
			res += "x_1 + x_2 = \\dfrac{-(" + b + ")}{" + a + "} = \\dfrac{" + (-b) + "}{" + a + "}";
			if(resultado.denominador != 1)
				res += " = " + resultado.showDfrac();
			res += " = \\mathbf{" + resultado.toStringLatex() + "}\\)";
		}
		else
		{
			res = "Pela relação de Vieta: \\(x_1 \\cdot x_2 = \\dfrac{c}{a}\\\\";
			res += "a = " + a + ", \\quad c = " + c + "\\\\";
			res += "x_1 \\cdot x_2 = \\dfrac{" + c + "}{" + a + "}";
			if(resultado.denominador != 1)
				res += " = " + resultado.showDfrac();
			res += " = \\mathbf{" + resultado.toStringLatex() + "}\\)";
		}

		gerarAlternativas(resultado);
		setResolucao(res);
	}
}
