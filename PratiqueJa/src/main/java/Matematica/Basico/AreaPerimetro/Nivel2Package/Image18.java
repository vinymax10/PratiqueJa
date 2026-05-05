package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloRetangulo;
import Modelo.Matematica.Conta;


public class Image18 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image18(int index)
	{
		super(index);
		
		int b = 2*(5 + rand.nextInt(13));
		int a = (int)(((double)b)*0.6);//altura
		int c = (int)(((double)b)*1.2);//hipotenusa
		
		String perimetro = "" + (b + a +c);
		
		textLatex = "Image41" + c + "-" + b + "-" + a;

		resultadoCorreto = "" + b;
		pergunta="Se o perímetro do triângulo é \\("+perimetro+"\\), qual o valor de \\(c\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTriangulo()+"\\\\";
		resolucaoLatex+="a="+a+",\\quad b="+b+"\\\\";
		resolucaoLatex+=a+"+"+b+"+c="+perimetro+"\\\\";
		resolucaoLatex+="c="+perimetro+"-"+a+"-"+b+"="+c;
		
		Config config = new ConfigTrianguloRetangulo(""+b, ""+a, "c",false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image18(1);
	}
}
