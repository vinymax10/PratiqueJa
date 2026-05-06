package matematica.avancado.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.avancado.equacaosegundograu.ResolucaoEq2Grau;
import modelo.matematica.Conta;

public class Expressao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao3(int index)
	{
		super(index);
		int x = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if (rand.nextBoolean())
			a *= -1;

		int b = delta - (2 * a * x);
		int c = (-x * delta) + (a * x * x);

		textLatex = "f(x)=";

		textLatex += Auxiliar.getNumber(a, "x^2", true);
		textLatex += Auxiliar.getNumber(b, "x", false);
		textLatex += Auxiliar.getNumber(c, "", false);

		int num = 1 + rand.nextInt(19);
		pergunta = "Encontre \\( f(" + num + ") \\)";

		int resultado = (a * num * num) + (b * num) + c;
		resultadoCorreto = "" + resultado;

		resolucaoLatex=ResolucaoEq2Grau.resolucao(a,b,c,num);
	}

	public static void main(String[] args)
	{
		new Expressao3(1);
	}

}
