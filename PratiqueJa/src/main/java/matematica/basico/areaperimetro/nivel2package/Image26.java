package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;
import modelo.matematica.Conta;


public class Image26 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image26(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		int perimetro= 2*(b+h);
		
		resultadoCorreto = "" + b;
		textLatex = "Image26" + b + "-" + h;
		pergunta="Se o perímetro do retângulo é \\("+perimetro+"\\), qual o valor de \\(b\\)?";
		
		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroRetangulo()+"\\\\";
		resolucaoLatex+="h="+h+"\\\\";
		resolucaoLatex+="2 \\cdot(b + "+h+")="+perimetro+"\\\\";
		resolucaoLatex+="b + "+h+"=\\dfrac{"+perimetro+"}{2}="+(b+h)+"\\\\";
		resolucaoLatex+="b="+(b+h)+"-"+h+"="+b+"\\\\";
		
		ConfigRetangulo config=new ConfigRetangulo("b",""+h,false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image26(1);
	}
}
