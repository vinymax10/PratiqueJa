package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config5;
import matematica.intermediario.anguloinscritocircunferencia.config.DadosConfig5;

public class Image7 extends GeradorExercicio
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

		String arcoDir = 2 * metadeArcoDir + "°";

		String resultadoCorreto = "" + 2 * metadeArcoEsq + "°";

		String resolucao = "";
		MyExpression expressao = new MyExpression(anguloCentral + "+c=180");
		resolucao += expressao.resolverLatex() + "\\\\";

		resolucao += "a=\\dfrac{" + (2 * metadeArcoDir) + "}{2}=" + metadeArcoDir + "\\\\";

		resolucao += "a+b+c=180\\\\";

		int c = 180 - metadeArcoEsq - metadeArcoDir;
		expressao = new MyExpression(c + "+" + metadeArcoDir + "+b=180");
		resolucao += expressao.resolverLatex() + "\\\\";

		expressao = new MyExpression("x=2*" + metadeArcoEsq);
		resolucao += expressao.resolverLatex();

		DadosConfig5 dados = new DadosConfig5();
		dados.lateralEsq = "x";
		dados.centroDir = anguloCentral + "°";
		dados.lateralDir = arcoDir;

		Config5 config = new Config5(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
