package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo2;
import modelo.matematica.Conta;


public class Image28 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image28(int index)
	{
		super(index);
		int h = 2*(2 + rand.nextInt(10));
		int b = (int)(((double)h)*1.35);
		int raio = h/2;

		String perimetro = "" + b * raio * 2;
		textLatex = "Image28" + b + "-" + h;

		resultadoCorreto = "" + raio;
		pergunta="Se o perímetro do retângulo é \\("+perimetro+"\\), qual o valor de \\(r\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroRetangulo()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+="2 \\cdot("+b+" + h)="+perimetro+"\\\\";
		resolucaoLatex+=b+" + h=\\dfrac{"+perimetro+"}{2}="+(b+h)+"\\\\";
		resolucaoLatex+="h="+(b+h)+"-"+b+"="+h+"\\\\";
		resolucaoLatex+="2r="+h+"\\\\";
		resolucaoLatex+="r=\\dfrac{"+h+"}{"+2+"}="+raio+"\\\\";

		ConfigRetangulo2 config=new ConfigRetangulo2(""+b,"h","r",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image28(1);
	}
}
