package matematica.avancado.numeroscomplexos.nivel1package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo a = NumeroComplexo.contruir(30);
		NumeroComplexo b = NumeroComplexo.contruir(30);

		String texto = "(" + a + ")-(" + b + ")=";
		String resolucao = a.resolucaoMinus(b);
		NumeroComplexo resultado = a.minus(b);

		String pergunta, resultadoCorreto, passoFinal;
		boolean real = rand.nextBoolean();
		if(real)
		{
			pergunta = "Qual a parte real da subtração?";
			resultadoCorreto = "" + resultado.real;
			passoFinal = "\\(\\operatorname{Re}(z)=\\mathbf{" + resultado.real.showDfrac() + "}\\)";
		}
		else
		{
			pergunta = "Qual a parte imaginária da subtração?";
			resultadoCorreto = "" + resultado.imaginaria;
			passoFinal = "\\(\\operatorname{Im}(z)=\\mathbf{" + resultado.imaginaria.showDfrac() + "}\\)";
		}

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
		addResolucao(passoFinal);
	}
}
