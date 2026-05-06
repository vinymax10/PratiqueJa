package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrapezio;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image13 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image13(int index)
	{
		super(index);

		int h = 2*(2 + rand.nextInt(8));
		int b = (int)(((double)h)*1.2);
		int B = (int)(((double)h)*2.1);
		
		String area = "" + (B + b) * h/2;
		
		textLatex = "Image13" + h + "-"+b+ "-"+B;

		resultadoCorreto = "" + B;
		pergunta="Se a área do trapézio é \\("+area+"\\), qual o valor de \\(B\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaTrapezio()+"\\\\";
		resolucaoLatex+="b="+b+",\\quad h="+h+"\\\\";
		resolucaoLatex+="\\dfrac{(B+"+b+")\\cdot"+h+"}{2} = "+area+"\\\\";
		MyExpression expressao = new MyExpression("(B+"+b+")*"+h+"="+area+"*2");
		resolucaoLatex+=expressao.resolverLatex();
		
		Config config = new ConfigTrapezio("B", b+"", h+"","","",true);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image13(1);
	}
}
