package matematica.avancado.pa.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 4 + rand.nextInt(20);

		Racional a1 = new Racional(1 + rand.nextInt(20));
		Racional r = new Racional(1 + rand.nextInt(20));

		Racional an = ResolucaoPA.a(a1, r, n);

		String enunciado = "" + a1.showDfrac() + ", " + ResolucaoPA.a(a1, r, 2).showDfrac() + ", " + ResolucaoPA.a(a1, r, 3).showDfrac() + ", \\ldots";

		addParagrafo("Qual é o " + n + "º termo?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(an.toString());
		for(String passo : ResolucaoPA.n_esimo(a1, r, n))
			addResolucao("\\(" + passo + "\\)");
	}
}
