package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config1;
import modelo.matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		int x = 30 + rand.nextInt(30);
		int a = x * 2;

		String strA = a + "°";

		resultadoCorreto = "" + x + "°";

		textLatex = strA;
		
		MyExpression expressao = new MyExpression("x="+a+"/2");
		resolucaoLatex = expressao.resolverLatex();
		
		Config1 config=new Config1("x", strA, "");
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
//		Graphics.salvar(image, true, "areaPoligono.PNG");
	}
	
	public static void main(String[] args)
	{
		new Image1(1);
	}

}
