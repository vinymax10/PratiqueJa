package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config1;
import modelo.matematica.Conta;


public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(30);
		int x = a * 2;

		String strA = a + "°";

		resultadoCorreto = "" + x + "°";

		textLatex = strA;

		MyExpression expressao = new MyExpression("x="+a+"*2");
		resolucaoLatex = expressao.resolverLatex();

		Config1 config=new Config1(strA, "", "x" );
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}

}
