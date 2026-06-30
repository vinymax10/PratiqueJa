package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config6;
import matematica.intermediario.anguloinscritocircunferencia.config.DadosConfig6;

public class Image10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 55 + rand.nextInt(20);
		int intAnguloExterno = 10 + rand.nextInt(20);
		int c = a - intAnguloExterno;

		String lateralDir = 2 * c + "°";
		String anguloExterno = intAnguloExterno + "°";

		String resultadoCorreto = "" + 2 * a + "°";

		MyExpression expressao = new MyExpression("b+" + intAnguloExterno + "+" + c + "=180");
		String resolucao1 = expressao.resolverLatex();
		int b = 180 - a;

		expressao = new MyExpression(b + "+a=180");
		String resolucao2 = expressao.resolverLatex();

		expressao = new MyExpression("x=2*" + a);
		String resolucao3 = expressao.resolverLatex();
		int lastSep = resolucao3.lastIndexOf("\\\\");
		String ultimoPasso = (lastSep >= 0) ? resolucao3.substring(lastSep + 2).trim() : resolucao3.trim();
		int lastEq = ultimoPasso.lastIndexOf('=');
		if (lastEq >= 0)
		{
			String boldado = ultimoPasso.substring(0, lastEq + 1) + "\\mathbf{" + ultimoPasso.substring(lastEq + 1).trim() + "}";
			resolucao3 = (lastSep >= 0) ? resolucao3.substring(0, lastSep + 2) + boldado : boldado;
		}

		DadosConfig6 dados = new DadosConfig6();
		dados.lateralEsq = "x";
		dados.lateralDir = lateralDir;
		dados.anguloExterno = anguloExterno;

		Config6 config = new Config6(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(c=\\dfrac{" + (2 * c) + "}{2}=" + c + "\\)");
		addResolucao("\\(" + resolucao1 + "\\)");
		addResolucao("\\(" + resolucao2 + "\\)");
		addResolucao("\\(" + resolucao3 + "\\)");
	}
}
