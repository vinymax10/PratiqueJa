package matematica.basico.racionais.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;

public class Racionais2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(20);
		int b = 2 + rand.nextInt(20);
		while(a == b)
			b = 2 + rand.nextInt(20);

		int c = 2 + rand.nextInt(20);
		int d = 2 + rand.nextInt(20);

		while(b == d)
			d = 2 + rand.nextInt(20);

		Racional aRacional = new Racional(a, b);
		Racional bRacional = new Racional(c, d);

		String enunciado = aRacional.showDfrac() + "-" + bRacional.showDfrac() + "=";

		Racional resultado = aRacional.minus(bRacional);
		resultado.fatoracao(2);

		String resolucao = ResolucaoRacionais.resolucaoCompleta(a, b, c, d, false);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(resultado.toString());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
