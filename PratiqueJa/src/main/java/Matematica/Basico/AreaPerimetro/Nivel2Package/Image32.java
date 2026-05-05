package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrapezio;
import Modelo.Matematica.Conta;


public class Image32 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image32(int index)
	{
		super(index);
		
		int B = 2*(5 + rand.nextInt(15));
		int b = (int)(((double)B)*0.65);
		int l = (int)(((double)B)*0.7);
		
		String perimetro = "" + (B+b+l+l);
		
		textLatex = "Image32" + l + "-" + b + "-" + B;

		resultadoCorreto = "" + B;
		pergunta="Se o perímetro do trapézio é \\("+perimetro+"\\), qual o valor de \\(a\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTrapezio()+"\\\\";
		resolucaoLatex+="b="+b+",\\quad c="+l+",\\quad d="+l+"\\\\";
		resolucaoLatex+="a+"+b+"+"+l+"+"+l+"="+perimetro+"\\\\";
		resolucaoLatex+="a="+perimetro+"-"+b+"-"+l+"-"+l+"="+B;
		
		Config config = new ConfigTrapezio("a", b+"", "",l+"",l+"",false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image32(1);
	}
}
