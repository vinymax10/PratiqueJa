package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigParalelogramo;
import Modelo.Matematica.Conta;


public class Image30 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image30(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int a = (int)(((double)b)*0.7);
		
		String perimetro = "" + (2*(b+a));
		
		textLatex = "Image30" + b + "-"+a;

		resultadoCorreto = "" + b;
		pergunta="Se o perímetro do paralelogramo é \\("+perimetro+"\\), qual o valor de \\(b\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroParalelogramo()+"\\\\";
		resolucaoLatex+="a="+a+"\\\\";
		resolucaoLatex+="2 \\cdot("+a+" + b)="+perimetro+"\\\\";
		resolucaoLatex+=a+"+ b =\\dfrac{"+perimetro+"}{2}="+(b+a)+"\\\\";
		resolucaoLatex+="b="+(b+a)+"-"+a+"="+b+"\\\\";

		ConfigParalelogramo config=new ConfigParalelogramo("b","",""+a,false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image30(1);
	}
}
