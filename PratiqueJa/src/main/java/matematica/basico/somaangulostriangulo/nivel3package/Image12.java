package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image12 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image12(int index)
	{
		super(index);

		int a = 35 + rand.nextInt(20);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 90 - a;
		int d = 180 - b - (c * x);

		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strC = expressao.imprimir();

//		12- Triangulo8 a, C | 
		ConfigTriangulo8 config = new ConfigTriangulo8(a, b, 180 - b);
		config.a.mostrar();
		config.c.mostrar();
		config.c.nome = strC;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression("y+" + a + "+90=180");
		resolucaoLatex = resolucao.resolverLatex();

		resolucao = new MyExpression(b + "+" + strC + "=180");
		resolucaoLatex += "\\\\" + resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.b.mostrar();
		config.b.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image12(1);
	}
}
