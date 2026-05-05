package Matematica.Avancado.EquacaoSegundoGrau.Nivel2Package;

import Matematica.Auxiliar;
import Matematica.Racional;
import Matematica.Avancado.EquacaoSegundoGrau.ResolucaoEq2Grau;
import Modelo.Matematica.Conta;

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

		pergunta = "Encontre o \\( x \\) do vértice";

		Racional resultado = new Racional(-b).div(new Racional(2 * a));
		resultado.fatoracao(2);
		resultadoCorreto = "" + resultado;

		resolucaoLatex=ResolucaoEq2Grau.resolucaoXVertice(a,b,c);
	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}

}
