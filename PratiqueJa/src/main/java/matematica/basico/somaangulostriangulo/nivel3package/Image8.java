package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int index)
	{
		super(index);

		int a = 50 + rand.nextInt(30);
		int b = 180 - (2 * a);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int e = 1 + rand.nextInt(20);

		while (e == c)
			e = 1 + rand.nextInt(20);

		int f = 180 - a - (e * x);

		MyExpression expressao = new MyExpression(e + "x+" + f);
		String strE = expressao.imprimir();

//		8- Triangulo6 c, E | 
		ConfigTriangulo6 config = new ConfigTriangulo6(a, b, a, 180 - a, 180 - a);
		config.e.mostrar();
		config.c.mostrar();
		config.e.nome = strE;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression("y=" + a);
		resolucaoLatex = resolucao.resolverLatex();

		resolucao = new MyExpression(a + "+" + strE + "=180");
		resolucaoLatex += "\\\\" + resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.a.mostrar();
		config.a.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}
}
