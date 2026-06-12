package matematica.avancado.numeroscomplexos.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo a = NumeroComplexo.contruirFrac(10);
		NumeroComplexo b = NumeroComplexo.contruirFrac(10);

		String texto = "(" + a + ")-(" + b + ")=";
		texto = texto.replace("(", "\\left(").replace(")", "\\right)");

		String resolucao = a.resolucaoMinus(b);
		resolucao = resolucao.replace("(", "\\left(").replace(")", "\\right)");

		NumeroComplexo resultado = a.minus(b);

		String pergunta, resultadoCorreto;
		boolean real = rand.nextBoolean();
		if(real)
		{
			pergunta = "Qual a parte real da subtração?";
			resultadoCorreto = "" + resultado.real;
			resolucao += "\\\\ \\operatorname{Re}(z)=" + resultado.real.showDfrac();
		}
		else
		{
			pergunta = "Qual a parte imaginária da subtração?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucao += "\\\\ \\operatorname{Im}(z)=" + resultado.imaginaria.showDfrac();
		}

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
