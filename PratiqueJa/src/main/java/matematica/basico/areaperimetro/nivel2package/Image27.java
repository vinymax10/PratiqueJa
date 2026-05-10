package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;
import modelo.matematica.Conta;


public class Image27 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image27(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		int perimetro= 2*(b+h);
		
		resultadoCorreto = "" + h;
		
		textLatex = "Image27" + b + "-" + h;
		
		pergunta="Se o perímetro do retângulo é \\("+perimetro+"\\), qual o valor de \\(h\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroRetangulo()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+="2 \\cdot("+b+" + h)="+perimetro+"\\\\";
		resolucaoLatex+=b+" + h=\\dfrac{"+perimetro+"}{2}="+(b+h)+"\\\\";
		resolucaoLatex+="h="+(b+h)+"-"+b+"="+h+"\\\\";
		
		ConfigRetangulo config=new ConfigRetangulo(""+b,"h",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image27(1);
	}
}
