package Matematica.Avancado.EquacaoSegundoGrau.Nivel1Package;

import Matematica.Auxiliar;
import Matematica.Avancado.EquacaoSegundoGrau.ResolucaoEq2Grau;
import Modelo.Matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int index)
	{
		super(index);

		int x = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if (rand.nextBoolean())
			a *= -1;

		int b = -delta - (2 * a * x);
		int c = (+x * delta) + (a * x * x);

		textLatex = "";

		textLatex += Auxiliar.getNumber(a, "x^2", true);
		textLatex += Auxiliar.getNumber(b, "x", false);
		textLatex += Auxiliar.getNumber(c, "", false);

		if (a < 0)
			pergunta = "Encontre a raiz maior";
		else
			pergunta = "Encontre a raiz menor";

		resultadoCorreto = "" + x;
		resolucaoLatex=ResolucaoEq2Grau.resolucaoX2(a,b,c);
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}

}
