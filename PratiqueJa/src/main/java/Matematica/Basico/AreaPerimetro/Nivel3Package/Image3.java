package Matematica.Basico.AreaPerimetro.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadradoCircunferencia;
import Modelo.Matematica.Conta;


public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);

		int a = 1 + rand.nextInt(10);

		textLatex = "Image3" + a + "";
		String area = "" + a * 2 * a;
		
		resultadoCorreto = "" + a;
		pergunta="Se a área do quadrado é \\("+area+"\\), qual o valor de \\(r\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaQuadrado()+"\\\\";
		resolucaoLatex+="l^2="+area+"\\\\";
		resolucaoLatex+="l="+"\\sqrt{"+area+"} = "+a+"\\sqrt{2}\\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+", \\quad ";
		resolucaoLatex+="d=2r\\\\";
		resolucaoLatex+="2r=l\\sqrt{2}\\\\";
		resolucaoLatex+="2r="+a+"\\sqrt{2} \\cdot \\sqrt{2}\\\\";
		resolucaoLatex+="2r="+a+"(\\sqrt{2})^2 = "+a+"\\cdot 2 ="+(a * 2)+"\\\\";
		resolucaoLatex+="r=\\dfrac{"+(a * 2)+"}{2} = "+a+"\\\\";

		ConfigQuadradoCircunferencia config=new ConfigQuadradoCircunferencia("r","l",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image3(1);
	}
}
