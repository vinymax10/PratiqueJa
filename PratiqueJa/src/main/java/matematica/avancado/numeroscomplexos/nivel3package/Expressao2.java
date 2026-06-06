package matematica.avancado.numeroscomplexos.nivel3package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo a = NumeroComplexo.contruir(10);

		String texto = "(" + a + ")^2=";
		String resolucao = a.resolucaoQuadrado();
		NumeroComplexo resultado = a.mult(a);

		String pergunta, resultadoCorreto;
		boolean real = rand.nextBoolean();
		if(real)
		{
			pergunta = "Qual a parte real do quadrado?";
			resultadoCorreto = "" + resultado.real;
			resolucao += "\\\\ \\text{Parte real}=" + resultado.real.showDfrac();
		}
		else
		{
			pergunta = "Qual a parte imaginária do quadrado?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucao += "\\\\ \\text{Parte imaginária}=" + resultado.imaginaria.showDfrac();
		}

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
