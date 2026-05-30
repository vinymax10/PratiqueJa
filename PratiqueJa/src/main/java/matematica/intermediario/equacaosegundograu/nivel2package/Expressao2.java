package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.Racional;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;
import modelo.matematica.Conta;

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

		int b = delta - (2 * a * x);
		int c = (-x * delta) + (a * x * x);

		textLatex = "";

		textLatex += Auxiliar.getNumber(a, "x^2", true);
		textLatex += Auxiliar.getNumber(b, "x", false);
		textLatex += Auxiliar.getNumber(c, "", false);

		pergunta = "Encontre o \\( y \\) do vértice";

		Racional resultado = new Racional(-(delta * delta)).div(new Racional(4 * a));
		resultado.fatoracao(2);
		resultadoCorreto = "" + resultado;

		resolucaoLatex=ResolucaoEq2Grau.resolucaoYVertice(a,b,c);
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}

}
