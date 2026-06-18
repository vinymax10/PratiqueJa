package matematica.intermediario.funcaoafim.nivel1package;

import matematica.DefinicaoCores;
import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9);
		int b = 1 + rand.nextInt(20);

		if(rand.nextBoolean())
			a *= -1;

		if(rand.nextBoolean())
			b *= -1;

		String nomeA = "";
		if(a != 1 && a != -1)
			nomeA = "" + a;
		else if(a == -1)
			nomeA = "-";

		String nomeB = "";
		if(b > 0)
			nomeB = "+";

		nomeB += b;

		String texto = "f(x)=" + nomeA + "x" + nomeB;

		Racional resultado = new Racional(b);

		texto = texto.replace("(", "\\left(").replace(")", "\\right)");

		String resolucao = "\\("+DefinicaoCores.irisBabypink()+"\\)";
		resolucao += "O coeficiente linear da reta formada pela função "
		+"\\(f(x)=" + nomeA + "x" + "\\textcolor{iris}{" + nomeB + "} \\)"
		+ " é  \\(\\textcolor{iris}{" + b + "}\\)";

		addParagrafo("Encontre o coeficiente linear");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultado.toString());
		setResolucao( resolucao );
	}
}
