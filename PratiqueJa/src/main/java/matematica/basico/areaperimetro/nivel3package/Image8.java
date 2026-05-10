package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo2;
import modelo.matematica.Conta;


public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int index)
	{
		super(index);
		int h = 2*(2 + rand.nextInt(10));
		int b = (int)(((double)h)*1.35);
		int raio = h/2;

		String area = "" + b * raio * 2;
		textLatex = "Image8" + b + "-" + h;

		resultadoCorreto = "" + raio;
		pergunta="Se a área do retângulo é \\("+area+"\\), qual o valor de \\(r\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+=b+"h="+area+"\\\\";
		resolucaoLatex+="h=\\dfrac{"+area+"}{"+b+"}="+h+"\\\\";
		resolucaoLatex+="2r="+h+"\\\\";
		resolucaoLatex+="r=\\dfrac{"+h+"}{"+2+"}="+raio+"\\\\";

		ConfigRetangulo2 config=new ConfigRetangulo2(""+b,"h","r",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}
}
