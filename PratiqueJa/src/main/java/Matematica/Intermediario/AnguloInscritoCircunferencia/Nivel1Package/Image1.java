package Matematica.Intermediario.AnguloInscritoCircunferencia.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Expressao.MyExpression;
import Matematica.Intermediario.AnguloInscritoCircunferencia.Config.Config1;
import Modelo.Matematica.Conta;

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
