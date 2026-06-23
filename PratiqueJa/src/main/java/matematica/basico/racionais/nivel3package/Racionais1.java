package matematica.basico.racionais.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;

public class Racionais1 extends GeradorExercicio
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
		while(c == d)
			d = 2 + rand.nextInt(20);

		Racional aRacional = new Racional(a, b);
		Racional bRacional = new Racional(c, d);

		String enunciado = aRacional.showDfrac() + "\\cdot" + bRacional.showDfrac() + "=";

		Racional resultado = aRacional.mult(bRacional);
		resultado.fatoracao(2);

		String[] resolucao = ResolucaoRacionais.Multiplicacao(a, b, c, d);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(resultado.toString());
		for(String passo : resolucao)
			addResolucao("\\(" + passo + "\\)");
	}
}
