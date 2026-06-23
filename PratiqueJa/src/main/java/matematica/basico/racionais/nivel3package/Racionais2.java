package matematica.basico.racionais.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;

public class Racionais2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		while(a == b)
			b = 2 + rand.nextInt(20);

		int c = 1 + rand.nextInt(10);
		int d = 1 + rand.nextInt(10);
		while(c == d)
			d = 2 + rand.nextInt(20);

		Racional aRacional = new Racional(a, b);
		Racional bRacional = new Racional(c, d);

		String enunciado = aRacional.showDfrac() + "\\div" + bRacional.showDfrac() + "=";

		Racional resultado = aRacional.div(bRacional);
		resultado.fatoracao(2);

		String[] resolucao = ResolucaoRacionais.divisao(a, b, c, d);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(resultado.toString());
		for(String passo : resolucao)
			addResolucao("\\(" + passo + "\\)");
	}
}
