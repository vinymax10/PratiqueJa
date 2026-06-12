package matematica.avancado.pa.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 4 + rand.nextInt(12);

		Racional a1 = new Racional(10 + rand.nextInt(15));
		int[] dens = {2, 3, 4};
		int d = dens[rand.nextInt(dens.length)];
		Racional r = new Racional(-(1 + rand.nextInt(3)), d);
		r.fatoracao(2);

		Racional an = ResolucaoPA.a(a1, r, n);

		String enunciado = a1.showDfrac() + ", " + ResolucaoPA.a(a1, r, 2).showDfrac()
				+ ", " + ResolucaoPA.a(a1, r, 3).showDfrac() + ", \\ldots";

		String resolucao = ResolucaoPA.n_esimo(a1, r, n);
		resolucao = resolucao.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Qual é o " + n + "º termo da PA decrescente?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(an.toString());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
