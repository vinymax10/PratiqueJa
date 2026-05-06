package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrapezio2;
import modelo.matematica.Conta;


public class Image35 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image35(int index)
	{
		super(index);
		
		int a = 2*(3 + rand.nextInt(13));
		int b = (int)(((double)a)*0.65);
		int h = (int)(((double)a)*0.55);
		int l = (int)(((double)a)*0.7);
		
		String perimetro = "" + (a+b+h+l);
		
		textLatex = "Image35" + l + "-" + b + "-" + a+" - "+h;

		resultadoCorreto = "" + b;
		pergunta="Se o perímetro do trapézio é \\("+perimetro+"\\), qual o valor de \\(b\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTrapezio()+"\\\\";
		resolucaoLatex+="a="+a+",\\quad c="+h+",\\quad d="+l+"\\\\";
		resolucaoLatex+=a+"+b+"+h+"+"+l+"="+perimetro+"\\\\";
		resolucaoLatex+="b="+perimetro+"-"+a+"-"+l+"-"+h+"="+b;
		
		Config config = new ConfigTrapezio2(""+a, "b", ""+h,""+l,false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image35(1);
	}
}
