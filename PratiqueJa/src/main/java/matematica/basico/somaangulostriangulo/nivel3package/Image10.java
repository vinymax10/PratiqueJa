package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image10(int index)
	{
		super(index);

		int a = 50 + rand.nextInt(30);
		int b = 180 - (2 * a);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int e = 1 + rand.nextInt(20);

		while (e == c)
			e = 1 + rand.nextInt(20);

		int d = 180 - a - (c * x);

		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strD = expressao.imprimir();

//		10- Triangulo6 b, D | 
		ConfigTriangulo6 config = new ConfigTriangulo6(a, b, a, 180 - a, 180 - a);
		config.d.mostrar();
		config.b.mostrar();
		config.d.nome = strD;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression("y+y+" + b + "=180");
		resolucaoLatex = resolucao.resolverLatex();

		resolucao = new MyExpression(a + "+" + strD + "=180");
		resolucaoLatex += resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.a.mostrar();
		config.a.nome = "y";

		config.c.mostrar();
		config.c.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image10(1);
	}
}
