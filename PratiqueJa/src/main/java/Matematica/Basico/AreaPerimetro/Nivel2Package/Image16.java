package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloIsosceles;
import Modelo.Matematica.Conta;


public class Image16 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image16(int index)
	{
		super(index);
		
		int b = 2*(5 + rand.nextInt(13));
		int a = (int)(((double)b)*0.7);//altura
		int c=a;
		
		int perimetro = (b + a +c);
		
		textLatex = "Image43" + c + "-" + b + "-" + a;

		resultadoCorreto = "" + b;
		pergunta="Se o perímetro do triângulo é \\("+perimetro+"\\), qual o valor de \\(b\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTrianguloIsosceles()+"\\\\";
		resolucaoLatex+="a="+a+"\\\\";
		resolucaoLatex+="2 \\cdot"+ a +"+b="+perimetro+"\\\\";
		resolucaoLatex+=(2*a)+"+b="+perimetro+"\\\\";
		resolucaoLatex+="b="+perimetro+"-"+(2*a)+"="+b;

		Config config = new ConfigTrianguloIsosceles("b", "","", ""+a,false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image16(1);
	}
}
