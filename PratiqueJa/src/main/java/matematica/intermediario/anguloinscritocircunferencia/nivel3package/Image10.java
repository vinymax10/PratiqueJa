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

		String resolucao = "";
		resolucao += "c=\\dfrac{" + (2 * c) + "}{2}=" + c + "\\\\";

		MyExpression expressao = new MyExpression("b+" + intAnguloExterno + "+" + c + "=180");
		resolucao += expressao.resolverLatex() + "\\\\";
		int b = 180 - a;

		expressao = new MyExpression(b + "+a=180");
		resolucao += expressao.resolverLatex() + "\\\\";

		expressao = new MyExpression("x=2*" + a);
		resolucao += expressao.resolverLatex();

		DadosConfig6 dados = new DadosConfig6();
		dados.lateralEsq = "x";
		dados.lateralDir = lateralDir;
		dados.anguloExterno = anguloExterno;

		Config6 config = new Config6(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
