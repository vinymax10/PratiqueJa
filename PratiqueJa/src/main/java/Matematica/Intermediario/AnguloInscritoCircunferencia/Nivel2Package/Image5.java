package Matematica.Intermediario.AnguloInscritoCircunferencia.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Expressao.MyExpression;
import Matematica.Intermediario.AnguloInscritoCircunferencia.Config.Config3;
import Modelo.Matematica.Conta;


public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);

		int x = 45 + rand.nextInt(30);
		int a = 180 - (2*x);

		String strA = a + "°";

		resultadoCorreto = "" + x + "°";

		textLatex = strA;

		MyExpression expressao = new MyExpression("2x+"+a+"=180");
		resolucaoLatex = expressao.resolverLatex();

		Config3 config=new Config3("x",strA);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}

}
