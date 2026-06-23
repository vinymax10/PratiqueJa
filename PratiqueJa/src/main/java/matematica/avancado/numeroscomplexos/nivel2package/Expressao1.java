package matematica.avancado.numeroscomplexos.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo a = NumeroComplexo.contruir(10);
		NumeroComplexo b = NumeroComplexo.contruir(10);

		String texto = "(" + a + ") \\cdot (" + b + ")=";
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
