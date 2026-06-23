package matematica.basico.racionais.nivel1package;

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
		int d = 2 + rand.nextInt(20);

		Racional aRacional = new Racional(a, d);
		Racional bRacional = new Racional(b, d);

		String enunciado = aRacional.showDfrac() + "+" + bRacional.showDfrac() + "=";

		Racional resultado = aRacional.add(bRacional);
		resultado.fatoracao(2);

		String[] resolucao = ResolucaoRacionais.simplesSoma(aRacional, bRacional);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(resultado.toString());
		for(String passo : resolucao)
			addResolucao("\\(" + passo + "\\)");
	}
}
