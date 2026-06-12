package matematica.avancado.numeroscomplexos.nivel3package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo a = NumeroComplexo.contruir(10);
		NumeroComplexo b = NumeroComplexo.contruir(10);

		String texto = "\\dfrac{" + a + "}{" + b + "}=";
		String resolucao = a.resolucaoDivisao(b);
		NumeroComplexo resultado = a.div(b);

		String pergunta, resultadoCorreto;
		boolean real = rand.nextBoolean();
		if(real)
		{
			pergunta = "Qual a parte real da divisão?";
			resultadoCorreto = "" + resultado.real;
			resolucao += "\\\\ \\operatorname{Re}(z)=" + resultado.real.showDfrac();
		}
		else
		{
			pergunta = "Qual a parte imaginária da divisão?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucao += "\\\\ \\operatorname{Im}(z)=" + resultado.imaginaria.showDfrac();
		}

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
