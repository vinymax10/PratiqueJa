package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigParalelogramo;
import modelo.matematica.Conta;


public class Image10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image10(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		String area = "" + b * h;
		
		textLatex = "Image10" + b + "-"+h;

		resultadoCorreto = "" + b;
		pergunta="Se a área do paralelogramo é \\("+area+"\\), qual o valor de \\(b\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="h="+h+"\\\\";
		resolucaoLatex+=h+"b="+area+"\\\\";
		resolucaoLatex+="b=\\dfrac{"+area+"}{"+h+"}="+b+"\\\\";
		
		ConfigParalelogramo config=new ConfigParalelogramo("b",""+h,"",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image10(1);
	}
}
