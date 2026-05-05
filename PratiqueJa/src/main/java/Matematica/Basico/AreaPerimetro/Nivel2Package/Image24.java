package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigParalelogramo;
import Modelo.Matematica.Conta;


public class Image24 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image24(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int a = (int)(((double)b)*0.7);
		
		String perimetro = "" + (2*(b+a));
		resultadoCorreto = "" + a;
		pergunta="Se o perímetro do paralelogramo é \\("+perimetro+"\\), qual o valor de \\(a\\)?";
		textLatex = "Image24" + b + "-" + a;

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroParalelogramo()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+="2 \\cdot(a + "+b+")="+perimetro+"\\\\";
		resolucaoLatex+="a +"+b+"=\\dfrac{"+perimetro+"}{2}="+(b+a)+"\\\\";
		resolucaoLatex+="a="+(b+a)+"-"+b+"="+a+"\\\\";

		ConfigParalelogramo config=new ConfigParalelogramo(""+b,"","a",false);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image24(1);
	}
}
