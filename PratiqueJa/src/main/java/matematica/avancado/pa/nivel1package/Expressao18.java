package matematica.avancado.pa.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int termo = 4 + rand.nextInt(20);
		int razao = 1 + rand.nextInt(20);
		int a1 = 1 + rand.nextInt(20);

		Racional a1Racional = new Racional(a1);
		Racional razaoRacional = new Racional(razao);
		Racional an = new Racional(a1);
		for(int i = 0; i < termo - 1; i++)
			an = an.add(razaoRacional);

		String enunciado = "" + a1Racional.showDfrac() + ", " + (a1Racional.add(razaoRacional).showDfrac()) + ", \\ldots, " + an.showDfrac() + "";
		enunciado = enunciado.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Quantos termos tem a PA?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas("" + termo);
		setResolucao("\\(" + ResolucaoPA.numeroTermos(a1Racional, razaoRacional, an) + "\\)");
	}
}
