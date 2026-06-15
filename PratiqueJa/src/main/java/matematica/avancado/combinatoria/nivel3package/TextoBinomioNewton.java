package matematica.avancado.combinatoria.nivel3package;

import java.util.Random;

public class TextoBinomioNewton
{
	static ProblemaBinomioNewton  problemas[]= {
	new ProblemaBinomioNewton("Na expansão de \\((x + $c)^{$n}\\), qual é o coeficiente do termo em \\(x^{$p}\\)?"),
	new ProblemaBinomioNewton("Determine o coeficiente do termo em \\(x^{$p}\\) na expansão de \\((x + $c)^{$n}\\)."),
	new ProblemaBinomioNewton("Pelo Binômio de Newton, qual o coeficiente de \\(x^{$p}\\) em \\((x + $c)^{$n}\\)?"),
	new ProblemaBinomioNewton("Expandindo \\((x + $c)^{$n}\\), qual é o coeficiente do termo que contém \\(x^{$p}\\)?"),
	new ProblemaBinomioNewton("Qual é o coeficiente numérico do termo em \\(x^{$p}\\) na expansão de \\((x + $c)^{$n}\\)?"),
	new ProblemaBinomioNewton("No desenvolvimento de \\((x + $c)^{$n}\\), determine o coeficiente do termo em \\(x^{$p}\\)."),
	new ProblemaBinomioNewton("Considere a expansão de \\((x + $c)^{$n}\\). Qual o coeficiente do termo em \\(x^{$p}\\)?"),
	new ProblemaBinomioNewton("Usando o termo geral do Binômio de Newton, qual o coeficiente de \\(x^{$p}\\) em \\((x + $c)^{$n}\\)?")
	};

	public static ProblemaBinomioNewton getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
