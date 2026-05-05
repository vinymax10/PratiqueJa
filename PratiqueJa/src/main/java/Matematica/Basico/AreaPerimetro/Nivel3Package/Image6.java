package Matematica.Basico.AreaPerimetro.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigRetangulo;
import Modelo.Matematica.Conta;


public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		int area=b * h;
		
		resultadoCorreto = "" + b;
		textLatex = "Image6" + b + "-" + h;
		pergunta="Se a área do retângulo é \\("+area+"\\), qual o valor de \\(b\\)?";
		
		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="h="+h+"\\\\";
		resolucaoLatex+=h+"b="+area+"\\\\";
		resolucaoLatex+="b=\\dfrac{"+area+"}{"+h+"}="+b+"\\\\";
		
		ConfigRetangulo config=new ConfigRetangulo("b",""+h,true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
