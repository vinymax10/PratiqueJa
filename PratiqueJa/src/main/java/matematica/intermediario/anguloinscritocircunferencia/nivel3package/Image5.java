package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config5;
import matematica.intermediario.anguloinscritocircunferencia.config.DadosConfig5;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int metadeArcoEsq = 30 + rand.nextInt(30);
		int metadeArcoDir = 30 + rand.nextInt(30);
		int anguloCentral = metadeArcoEsq + metadeArcoDir;

		if(metadeArcoEsq < metadeArcoDir)
		{
			int aux = metadeArcoDir;
			metadeArcoDir = metadeArcoEsq;
			metadeArcoEsq = aux;
		}

		String arcoEsq = 2 * metadeArcoEsq + "°";
		String arcoDir = 2 * metadeArcoDir + "°";
		String resultadoCorreto = "" + anguloCentral + "°";

		String resolucao = "";
		resolucao += "a=\\dfrac{" + (2 * metadeArcoDir) + "}{2}=" + metadeArcoDir + "\\\\";
		resolucao += "b=\\dfrac{" + (2 * metadeArcoEsq) + "}{2}=" + metadeArcoEsq + "\\\\";
		resolucao += "a+b+c=180\\\\";
		MyExpression expressao = new MyExpression(metadeArcoEsq + "+" + metadeArcoDir + "+c=180");
		resolucao += expressao.resolverLatex() + "\\\\";

		int c = 180 - metadeArcoEsq - metadeArcoDir;
		expressao = new MyExpression(c + "+x=180");
		resolucao += expressao.resolverLatex();

		DadosConfig5 dados = new DadosConfig5();
		dados.lateralEsq = arcoEsq;
		dados.centroEsq = "x";
		dados.lateralDir = arcoDir;

		Config5 config = new Config5(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
