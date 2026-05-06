package matematica.avancado.funcaoafim.nivel1package;

import matematica.DefinicaoCores;
import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int index)
	{
		super(index);

		int a = 1 + rand.nextInt(9);
		int b = 1 + rand.nextInt(20);

		if (rand.nextBoolean())
			a *= -1;

		if (rand.nextBoolean())
			b *= -1;

		String nomeA = "";
		if (a != 1 && a != -1)
			nomeA = "" + a;
		else if (a == -1)
			nomeA = "-";

		String nomeB = "";
		if (b > 0)
			nomeB = "+";

		nomeB += b;

		textLatex = "f(x)=" + nomeA + "x" + nomeB;

		Racional resultado = new Racional(a);

		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		pergunta = "Encontre o coeficiente angular";
		
		resultadoCorreto = "" + resultado.toString();
		
		resolucaoLatex=DefinicaoCores.irisBabypink();
		resolucaoLatex+="\\text{O coeficiente angular da reta } \\\\ "
		+ "\\text{formada pela função }  \\\\ "+"f(x)=\\textcolor{iris}{" + nomeA + "} x" + nomeB
		+ "\\text{ é } \\textcolor{iris}{"+a+"}";
		
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}
}
