package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigParalelogramo;
import modelo.matematica.Conta;


public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		String area = "" + b * h;
		resultadoCorreto = "" + h;
		pergunta="Se a área do paralelogramo é \\("+area+"\\), qual o valor de \\(h\\)?";
		textLatex = "Image4" + b + "-" + h;

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+=b+"h="+area+"\\\\";
		resolucaoLatex+="h=\\dfrac{"+area+"}{"+b+"}="+h+"\\\\";
		
		ConfigParalelogramo config=new ConfigParalelogramo(""+b,"h","",true);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
