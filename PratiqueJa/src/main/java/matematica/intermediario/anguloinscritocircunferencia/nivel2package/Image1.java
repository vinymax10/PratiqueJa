package matematica.intermediario.anguloinscritocircunferencia.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Auxiliar;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config1;
import modelo.matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);

		int a = 30 + rand.nextInt(30);

		int d = a - (c * x);

		String str1 = Auxiliar.getNumber(c,"x",true) + Auxiliar.getNumber(d,"",false);
		
		String strA = 2 * a + "°";

		resultadoCorreto = "" + x + "°";

		textLatex = strA;

		MyExpression expressao = new MyExpression(str1+"="+(2 * a)+"/2");
		resolucaoLatex = expressao.resolverLatex();

		Config1 config=new Config1(str1, "", strA);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}

}
