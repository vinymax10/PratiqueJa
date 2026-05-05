package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrapezio;
import Modelo.Matematica.Conta;


public class Image33 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image33(int index)
	{
		super(index);
		
		int a = 2*(5 + rand.nextInt(15));
		int b = (int)(((double)a)*0.65);
		int l = (int)(((double)a)*0.7);
		
		String perimetro = "" + (a+b+l+l);
		
		textLatex = "Image33" + l + "-" + b + "-" + a;

		resultadoCorreto = "" + b;
		pergunta="Se o perímetro do trapézio é \\("+perimetro+"\\), qual o valor de \\(b\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTrapezio()+"\\\\";
		resolucaoLatex+="a="+a+",\\quad c="+l+",\\quad d="+l+"\\\\";
		resolucaoLatex+=a+"+b+"+l+"+"+l+"="+perimetro+"\\\\";
		resolucaoLatex+="b="+perimetro+"-"+a+"-"+l+"-"+l+"="+b;
		
		Config config = new ConfigTrapezio(""+a, "b", "",l+"",l+"",false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image33(1);
	}
}
