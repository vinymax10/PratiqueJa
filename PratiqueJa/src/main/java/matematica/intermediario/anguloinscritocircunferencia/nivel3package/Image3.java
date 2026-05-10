package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config4;
import modelo.matematica.Conta;


public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);

		int a = 90 + rand.nextInt(20);
		int b = 30 + rand.nextInt(30);
		int c = (2 * a) - (2 * b);

		String strA = a + "°";
		String strB = b + "°";
		String strC = c + "°";
		resultadoCorreto = "" + a + "°";

		textLatex = strA;

		MyExpression expressao = new MyExpression(c+"+2*+"+b+"=2x");
		resolucaoLatex = expressao.resolverLatex();

		Config4 config=new Config4("x", strB, strC);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image3(1);
	}

}
