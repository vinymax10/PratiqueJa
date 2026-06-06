package matematica.avancado.numeroscomplexos.nivel1package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo a = NumeroComplexo.contruir(30);
		NumeroComplexo b = NumeroComplexo.contruir(30);

		String texto = "(" + a + ")+(" + b + ")=";
		String resolucao = a.resolucaoSoma(b);
		NumeroComplexo resultado = a.add(b);

		String pergunta, resultadoCorreto;
		boolean real = rand.nextBoolean();
		if(real)
		{
			pergunta = "Qual a parte real da soma?";
			resultadoCorreto = "" + resultado.real;
			resolucao += "\\\\ \\text{Parte real}=" + resultado.real.showDfrac();
		}
		else
		{
			pergunta = "Qual a parte imaginária da soma?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucao += "\\\\ \\text{Parte imaginária}=" + resultado.imaginaria.showDfrac();
		}

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
