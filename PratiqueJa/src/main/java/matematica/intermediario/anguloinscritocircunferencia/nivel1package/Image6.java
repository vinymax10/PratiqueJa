package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config1;
import modelo.matematica.Conta;


public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);

		int a = 70 + rand.nextInt(40);
		int x = a;

		String strA = a + "°";

		resultadoCorreto = "" + x + "°";

		textLatex = strA;
		
		MyExpression expressao = new MyExpression("x="+a);
		resolucaoLatex = expressao.resolverLatex();

		Config1 config=new Config1("", "x", strA );
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}

}
