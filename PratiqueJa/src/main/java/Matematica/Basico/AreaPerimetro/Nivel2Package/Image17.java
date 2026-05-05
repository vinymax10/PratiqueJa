package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloIsosceles;
import Modelo.Matematica.Conta;


public class Image17 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image17(int index)
	{
		super(index);
		
		int b = 2*(5 + rand.nextInt(13));
		int a = (int)(((double)b)*0.7);//altura
		int c=a;
		
		int perimetro = (b + a +c);
		
		textLatex = "Image42" + c + "-" + b + "-" + a;

		resultadoCorreto = "" + b;
		pergunta="Se o perímetro do triângulo é \\("+perimetro+"\\), qual o valor de \\(a\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTrianguloIsosceles()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+="2 \\cdot a +"+b+"="+perimetro+"\\\\";
		resolucaoLatex+="2 \\cdot a="+perimetro+"-"+b+"="+(perimetro-b)+"\\\\";
		resolucaoLatex+="a=\\dfrac{"+(perimetro-b)+"}{2}="+a;

		Config config = new ConfigTrianguloIsosceles(""+b, "","", "a",false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image17(1);
	}
}
