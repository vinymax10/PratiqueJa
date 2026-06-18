package matematica.avancado.numeroscomplexos.nivel3package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo a = NumeroComplexo.contruirModulo(30);

		addParagrafo("Calcule \\(|z|\\)");
		addParagrafo("\\(z=" + a + "\\)");
		gerarAlternativas("" + a.modulo);
		setResolucao("\\(" + a.resolucaoModulo() + "\\)");
	}
}
