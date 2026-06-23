package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config6;
import matematica.intermediario.anguloinscritocircunferencia.config.DadosConfig6;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 55 + rand.nextInt(20);
		int intAnguloExterno = 10 + rand.nextInt(20);
		int c = a - intAnguloExterno;

		String lateralEsq = 2 * a + "°";
		String lateralDir = 2 * c + "°";

		String resultadoCorreto = "" + intAnguloExterno + "°";

		MyExpression expressao = new MyExpression(a + "+b=180");
		String resolucao1 = expressao.resolverLatex();
		int b = 180 - a;

		expressao = new MyExpression("x+" + b + "+" + c + "=180");
		String resolucao2 = expressao.resolverLatex();

		DadosConfig6 dados = new DadosConfig6();
		dados.lateralEsq = lateralEsq;
		dados.lateralDir = lateralDir;
		dados.anguloExterno = "x";

		Config6 config = new Config6(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(a=\\dfrac{" + (2 * a) + "}{2}=" + a + "\\)");
		addResolucao("\\(c=\\dfrac{" + (2 * c) + "}{2}=" + c + "\\)");
		addResolucao("\\(" + resolucao1 + "\\)");
		addResolucao("\\(x+b+c=180\\)");
		addResolucao("\\(" + resolucao2 + "\\)");
	}
}
