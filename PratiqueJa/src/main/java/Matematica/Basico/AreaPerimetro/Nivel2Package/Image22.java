package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadrado;
import Modelo.Matematica.Conta;

public class Image22 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image22(int index)
	{
		super(index);

		int a = 2 + rand.nextInt(18);

		String perimetro = "" + (4 * a) +"\\sqrt{2}";
		resultadoCorreto = "" + (a * 2);
		pergunta="Se o perímetro do quadrado é \\("+perimetro+"\\), qual o valor de \\(d\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="4 \\cdot l="+perimetro+"\\\\";
		resolucaoLatex+="l="+"\\dfrac{"+perimetro+"}{4}="+a+" \\sqrt{2} \\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+"\\\\";
		resolucaoLatex+="d="+a+"\\sqrt{2} \\cdot \\sqrt{2}\\\\";
		resolucaoLatex+="d="+a+"(\\sqrt{2})^2 = "+a+"\\cdot 2 ="+(a * 2)+"\\\\";

		ConfigQuadrado config=new ConfigQuadrado("l","d",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image22(1);
	}
}
