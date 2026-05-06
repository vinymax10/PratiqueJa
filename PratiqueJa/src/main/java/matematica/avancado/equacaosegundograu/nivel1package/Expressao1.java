package matematica.avancado.equacaosegundograu.nivel1package;

import matematica.Auxiliar;
import matematica.avancado.equacaosegundograu.ResolucaoEq2Grau;
import modelo.matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int index)
	{
		super(index);

		int x = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if (rand.nextBoolean())
			a *= -1;

		int b = delta - (2 * a * x);
		int c = (-x * delta) + (a * x * x);

		textLatex = "";

		textLatex += Auxiliar.getNumber(a, "x^2", true);
		textLatex += Auxiliar.getNumber(b, "x", false);
		textLatex += Auxiliar.getNumber(c, "", false);

		if (a < 0)
			pergunta = "Encontre a raiz menor";
		else
			pergunta = "Encontre a raiz maior";

		resultadoCorreto = "" + x;
		
		resolucaoLatex=ResolucaoEq2Grau.resolucaoX1(a,b,c);
	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}

}
