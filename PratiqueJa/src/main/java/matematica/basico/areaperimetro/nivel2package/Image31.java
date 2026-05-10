package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;
import modelo.matematica.Conta;


public class Image31 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image31(int index)
	{
		super(index);

		int l = 3 + rand.nextInt(20);

		String perimetro = "" + (4 * l);
		
		resultadoCorreto = "" + l;
		textLatex = "Image31" + l + "";

		pergunta="Se o perímetro do lozango é \\("+perimetro+"\\), qual o valor de \\(l\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="4 \\cdot l="+perimetro+"\\\\";
		resolucaoLatex+="l=\\dfrac{"+perimetro+"}{4}="+l+"\\\\";
		
		ConfigLozango config=new ConfigLozango("", "","","","l",false);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image31(1);
	}
}
