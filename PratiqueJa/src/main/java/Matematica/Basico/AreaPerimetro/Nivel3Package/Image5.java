package Matematica.Basico.AreaPerimetro.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadrado3;
import Modelo.Matematica.Conta;


public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);
		int a = 1 + rand.nextInt(10);

		String area = "" + (4 * a * a);
		resultadoCorreto = "" + a;
		pergunta="Se a área do quadrado é \\("+area+"\\), qual o valor de \\(r\\)?";
		textLatex = "Image5" + a+" - "+area;

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaQuadrado()+"\\\\";
		resolucaoLatex+="l^2="+area+"\\\\";
		resolucaoLatex+="l="+"\\sqrt{"+area+"} = "+(2*a)+"\\\\";
		resolucaoLatex+="r=\\dfrac{l}{2}=\\dfrac{"+(2*a)+"}{2}="+a+"\\\\";
		
		ConfigQuadrado3 config=new ConfigQuadrado3("l","r",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
