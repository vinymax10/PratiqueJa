package matematica.avancado.numeroscomplexos.nivel3package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo a = NumeroComplexo.contruirFrac(10);
		NumeroComplexo b = NumeroComplexo.contruirFrac(10);

		String texto = "(" + a + ") \\cdot (" + b + ")=";
		texto = texto.replace("(", "\\left(").replace(")", "\\right)");

		String resolucao = a.resolucaoMultiplicacao(b);
		NumeroComplexo resultado = a.mult(b);

		String pergunta, resultadoCorreto, passoFinal;
		boolean real = rand.nextBoolean();
		if(real)
		{
			pergunta = "Qual a parte real da multiplicação?";
			resultadoCorreto = "" + resultado.real;
			passoFinal = "\\(\\operatorname{Re}(z)=" + resultado.real.showDfrac() + "\\)";
		}
		else
		{
			pergunta = "Qual a parte imaginária da multiplicação?";
			resultadoCorreto = "" + resultado.imaginaria;
			passoFinal = "\\(\\operatorname{Im}(z)=" + resultado.imaginaria.showDfrac() + "\\)";
		}

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
		addResolucao(passoFinal);
	}
}
