package Matematica.Intermediario.AnguloInscritoCircunferencia.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Expressao.MyExpression;
import Matematica.Intermediario.AnguloInscritoCircunferencia.Config.Config1;
import Modelo.Matematica.Conta;


public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(30);
		int x = a * 2;

		String strA = a + "°";

		resultadoCorreto = "" + x + "°";

		textLatex = strA;
		
		MyExpression expressao = new MyExpression("x="+a+"*2");
		resolucaoLatex = expressao.resolverLatex();
		
		Config1 config=new Config1(strA, "x", "");
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}

}
