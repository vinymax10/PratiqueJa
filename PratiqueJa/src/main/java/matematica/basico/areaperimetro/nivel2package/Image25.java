package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado3;
import modelo.matematica.Conta;


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
