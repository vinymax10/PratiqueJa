package matematica.avancado.pa.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 4 + rand.nextInt(20);

		Racional a1 = new Racional(1 + rand.nextInt(20));
		Racional r = new Racional(1 + rand.nextInt(20));
		Racional an = ResolucaoPA.a(a1, r, n);

		String enunciado = "x" + "+ \\ldots + " + an.showDfrac() + "=" + ResolucaoPA.soma(a1, an, n).showDfrac();

		addParagrafo("Qual o valor de \\(x\\) na PA que possui " + n + " termos?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(a1.toString());
		setResolucao("\\(" + ResolucaoPA.resolucaoSoma3(a1, r, an, n) + "\\)");
	}
}
