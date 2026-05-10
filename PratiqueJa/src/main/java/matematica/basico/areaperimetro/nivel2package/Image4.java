package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado3;
import modelo.matematica.Conta;

//Quadrado com circunferencia dentro
public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);
		int raio = 1 + rand.nextInt(10);
		String strRaio = "" + raio;
		textLatex = "Image4" +raio;

		pergunta="Se \\(r="+strRaio+"\\), qual a área do quadrado?";

		resultadoCorreto = "" + (4 * raio * raio);
		int l=2*raio;
		resolucaoLatex="l=2r= 2 \\cdot"+raio+"="+l+"\\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaAreaQuadrado()+"\\\\";
		resolucaoLatex+="A="+l+"^2="+l+" \\cdot "+l+"="+(l*l);
		
		ConfigQuadrado3 config=new ConfigQuadrado3("l","r",true);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
