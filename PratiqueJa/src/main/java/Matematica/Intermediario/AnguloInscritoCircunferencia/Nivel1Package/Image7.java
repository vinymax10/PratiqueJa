package Matematica.Intermediario.AnguloInscritoCircunferencia.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Expressao.MyExpression;
import Matematica.Intermediario.AnguloInscritoCircunferencia.Config.Config2;
import Modelo.Matematica.Conta;


public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image7(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(30);
		int x = a;

		String strA = a + "°";

		resultadoCorreto = "" + x + "°";

		textLatex = strA;
		
		MyExpression expressao = new MyExpression("x="+a);
		resolucaoLatex = expressao.resolverLatex();

		Config2 config=new Config2(strA, "x");
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image7(1);
	}

}
