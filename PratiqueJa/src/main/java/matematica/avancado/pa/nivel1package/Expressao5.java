package matematica.avancado.pa.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(20));
		Racional r = new Racional(1 + rand.nextInt(20));

		String enunciado = "" + a1.showDfrac() + ", " + ResolucaoPA.a(a1, r, 2).showDfrac() + ", " + ResolucaoPA.a(a1, r, 3).showDfrac() + ", x";

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(ResolucaoPA.a(a1, r, 4).toString());
		for(String passo : ResolucaoPA.x4(a1, r))
			addResolucao("\\(" + passo + "\\)");
	}
}
