package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadrado3;
import Modelo.Matematica.Conta;


public class Image25 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image25(int index)
	{
		super(index);
		int r = 2 + rand.nextInt(18);
		int l=2*r;
		String perimetro = "" + (4 * l);
		resultadoCorreto = "" + r;
		pergunta="Se o perímetro do quadrado é \\("+perimetro+"\\), qual o valor de \\(r\\)?";
		textLatex = "Image25" + r+" - "+perimetro;

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="4 \\cdot l="+perimetro+"\\\\";
		resolucaoLatex+="l="+"\\dfrac{"+perimetro+"}{4}="+l+" \\\\";
		resolucaoLatex+="r=\\dfrac{l}{2}=\\dfrac{"+l+"}{2}="+r+"\\\\";
		
		ConfigQuadrado3 config=new ConfigQuadrado3("l","r",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image25(1);
	}
}
