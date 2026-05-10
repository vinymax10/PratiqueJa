package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;
import modelo.matematica.Conta;


public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		int area=b * h;
		
		resultadoCorreto = "" + b;
		textLatex = "Image6" + b + "-" + h;
		pergunta="Se a área do retângulo é \\("+area+"\\), qual o valor de \\(b\\)?";
		
		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="h="+h+"\\\\";
		resolucaoLatex+=h+"b="+area+"\\\\";
		resolucaoLatex+="b=\\dfrac{"+area+"}{"+h+"}="+b+"\\\\";
		
		ConfigRetangulo config=new ConfigRetangulo("b",""+h,true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
