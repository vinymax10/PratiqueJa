package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config2;
import modelo.matematica.Conta;


public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(30);
		int x = a;

		String strA = a + "°";

		resultadoCorreto = "" + x + "°";

		textLatex = strA;

		MyExpression expressao = new MyExpression("x="+a);
		resolucaoLatex = expressao.resolverLatex();

		Config2 config=new Config2("x", strA);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}

}
