package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadrado;
import Modelo.Matematica.Conta;

public class Image21 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image21(int index)
	{
		super(index);

		int l = 3 + rand.nextInt(20);

		String perimetro = "" + (4 * l);
		resultadoCorreto = "" + l;
		textLatex = "Image21" + l + "";

		pergunta="Se o perímetro do quadrado é \\("+perimetro+"\\), qual o valor de \\(l\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="4 \\cdot l="+perimetro+"\\\\";
		resolucaoLatex+="l=\\dfrac{"+perimetro+"}{4}="+l+"\\\\";

		ConfigQuadrado config=new ConfigQuadrado("l","",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image21(1);
	}
}
