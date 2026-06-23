package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config5;
import matematica.intermediario.anguloinscritocircunferencia.config.DadosConfig5;

public class Image6 extends GeradorExercicio
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

		String resultadoCorreto = "" + 2 * metadeArcoDir + "°";

		MyExpression expressao = new MyExpression(anguloCentral + "+c=180");
		String resolucao1 = expressao.resolverLatex();

		int c = 180 - metadeArcoEsq - metadeArcoDir;
		expressao = new MyExpression(c + "+" + metadeArcoEsq + "+a=180");
		String resolucao2 = expressao.resolverLatex();

		expressao = new MyExpression("x=2*" + metadeArcoDir);
		String resolucao3 = expressao.resolverLatex();

		DadosConfig5 dados = new DadosConfig5();
		dados.lateralEsq = arcoEsq;
		dados.centroDir = anguloCentral + "°";
		dados.lateralDir = "x";

		Config5 config = new Config5(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao1 + "\\)");
		addResolucao("\\(b=\\dfrac{" + (2 * metadeArcoEsq) + "}{2}=" + metadeArcoEsq + "\\)");
		addResolucao("\\(a+b+c=180\\)");
		addResolucao("\\(" + resolucao2 + "\\)");
		addResolucao("\\(" + resolucao3 + "\\)");
	}
}
