package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config4;
import modelo.matematica.Conta;


public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int a = 90 + rand.nextInt(20);
		int b = 30 + rand.nextInt(30);
		int c = (2 * a) - (2 * b);

		String strA = a + "°";
//		String strB = b + "°";
		String strC = c + "°";
		resultadoCorreto = "" + b + "°";

		textLatex = strA;

		MyExpression expressao = new MyExpression(c+"+2x=2*"+a);
		resolucaoLatex = expressao.resolverLatex();

		Config4 config=new Config4(strA, "x", strC);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}

}
