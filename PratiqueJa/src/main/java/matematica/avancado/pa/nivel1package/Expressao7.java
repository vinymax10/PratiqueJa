package matematica.avancado.pa.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 4 + rand.nextInt(15);

		int a1Val = 20 + rand.nextInt(30);
		int rVal = -(1 + rand.nextInt(5));
		Racional a1 = new Racional(a1Val);
		Racional r = new Racional(rVal);
		Racional an = ResolucaoPA.a(a1, r, n);

		String enunciado = a1.showDfrac() + ", " + ResolucaoPA.a(a1, r, 2).showDfrac()
				+ ", " + ResolucaoPA.a(a1, r, 3).showDfrac() + ", \\ldots";

		addParagrafo("Qual é o " + n + "º termo da PA decrescente?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(an.toString());
		for(String passo : ResolucaoPA.n_esimo(a1, r, n))
			addResolucao("\\(" + passo + "\\)");
	}
}
