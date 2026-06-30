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

		String pergunta, resultadoCorreto, passoFinal;
		boolean real = rand.nextBoolean();
		if(real)
		{
			pergunta = "Qual a parte real da soma?";
			resultadoCorreto = "" + resultado.real;
			passoFinal = "\\(\\operatorname{Re}(z)=\\mathbf{" + resultado.real.showDfrac() + "}\\)";
		}
		else
		{
			pergunta = "Qual a parte imaginária da soma?";
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
