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

		MyExpression expressao = new MyExpression(metadeArcoEsq + "+" + metadeArcoDir + "+c=180");
		String resolucao1 = expressao.resolverLatex();

		int c = 180 - metadeArcoEsq - metadeArcoDir;
		expressao = new MyExpression(c + "+x=180");
		String resolucao2 = expressao.resolverLatex();
		int lastSep = resolucao2.lastIndexOf("\\\\");
		String ultimoPasso = (lastSep >= 0) ? resolucao2.substring(lastSep + 2).trim() : resolucao2.trim();
		int lastEq = ultimoPasso.lastIndexOf('=');
		if (lastEq >= 0)
		{
			String boldado = ultimoPasso.substring(0, lastEq + 1) + "\\mathbf{" + ultimoPasso.substring(lastEq + 1).trim() + "}";
			resolucao2 = (lastSep >= 0) ? resolucao2.substring(0, lastSep + 2) + boldado : boldado;
		}

		DadosConfig5 dados = new DadosConfig5();
		dados.lateralEsq = arcoEsq;
		dados.centroEsq = "x";
		dados.lateralDir = arcoDir;

		Config5 config = new Config5(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(a=\\dfrac{" + (2 * metadeArcoDir) + "}{2}=" + metadeArcoDir + "\\)");
		addResolucao("\\(b=\\dfrac{" + (2 * metadeArcoEsq) + "}{2}=" + metadeArcoEsq + "\\)");
		addResolucao("\\(a+b+c=180\\)");
		addResolucao("\\(" + resolucao1 + "\\)");
		addResolucao("\\(" + resolucao2 + "\\)");
	}
}
