package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;
import modelo.matematica.Conta;


public class Image29 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image29(int index)
	{
		super(index);
		int h = 4 + rand.nextInt(16);

		resultadoCorreto = "" + h;
		int perimetro=2 * ((2 * h)+h);
		textLatex = "Image29" + h ;

		pergunta="Se o perímetro do retângulo é \\("+perimetro+"\\), qual o valor de \\(h\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroRetangulo()+"\\\\";
		resolucaoLatex+="2 \\cdot (2h + h)="+perimetro+"\\\\";
		resolucaoLatex+="2h + h=\\dfrac{"+perimetro+"}{2}="+(perimetro/2)+"\\\\";
		resolucaoLatex+="3h="+(perimetro/2)+"\\\\";
		resolucaoLatex+="h=\\dfrac{"+(perimetro/2)+"}{3}="+h+"\\\\";

		ConfigRetangulo config=new ConfigRetangulo("2h","h",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image29(1);
	}
}
