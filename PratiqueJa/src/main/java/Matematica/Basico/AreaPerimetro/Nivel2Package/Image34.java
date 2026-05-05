package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrapezio2;
import Modelo.Matematica.Conta;


public class Image34 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image34(int index)
	{
		super(index);
		
		int a = 2*(3 + rand.nextInt(13));
		int b = (int)(((double)a)*0.65);
		int h = (int)(((double)a)*0.55);
		int l = (int)(((double)a)*0.7);
		
		String perimetro = "" + (a+b+h+l);
		
		textLatex = "Image34" + l + "-" + b + "-" + a+" - "+h;

		resultadoCorreto = "" + a;
		pergunta="Se o perímetro do trapézio é \\("+perimetro+"\\), qual o valor de \\(a\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTrapezio()+"\\\\";
		resolucaoLatex+="b="+b+",\\quad c="+h+",\\quad d="+l+"\\\\";
		resolucaoLatex+="a+"+b+"+"+h+"+"+l+"="+perimetro+"\\\\";
		resolucaoLatex+="a="+perimetro+"-"+b+"-"+h+"-"+l+"="+a;
		
		Config config = new ConfigTrapezio2("a", ""+b, ""+h,""+l,false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image34(1);
	}
}
