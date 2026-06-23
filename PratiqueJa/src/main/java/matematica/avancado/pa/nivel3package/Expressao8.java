package matematica.avancado.pa.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 4 + rand.nextInt(20);

		Racional a1 = new Racional(1 + rand.nextInt(20), 1 + rand.nextInt(20));
		a1.fatoracao(2);

		Racional r = new Racional(1 + rand.nextInt(20), 1 + rand.nextInt(20));
		r.fatoracao(2);

		Racional an = ResolucaoPA.a(a1, r, n);

		String enunciado = "" + a1.showDfrac() + "+ \\ldots + " + an.showDfrac() + "=" + ResolucaoPA.soma(a1, an, n).showDfrac();

		addParagrafo("Quantos termos tem a PA?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas("" + n);
		for(String passo : ResolucaoPA.resolucaoSoma2(a1, r, an, n))
			addResolucao("\\(" + passo.replace("(", "\\left(").replace(")", "\\right)") + "\\)");
	}
}
