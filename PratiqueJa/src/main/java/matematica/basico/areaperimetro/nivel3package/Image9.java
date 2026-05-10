package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;
import modelo.matematica.Conta;


public class Image9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image9(int index)
	{
		super(index);
		int h = 4 + rand.nextInt(16);

		resultadoCorreto = "" + h;
		int area=h * (2 * h);
		textLatex = "Image9" + h ;

		pergunta="Se a área do retângulo é \\("+area+"\\), qual o valor de \\(h\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="2h \\cdot h="+area+"\\\\";
		resolucaoLatex+="2h^2="+area+"\\\\";
		resolucaoLatex+="h^2=\\dfrac{"+area+"}{"+2+"}="+(h*h)+"\\\\";
		resolucaoLatex+="h=\\sqrt{"+(h*h)+"}="+h+"\\\\";
		
		ConfigRetangulo config=new ConfigRetangulo("2h","h",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image9(1);
	}
}
