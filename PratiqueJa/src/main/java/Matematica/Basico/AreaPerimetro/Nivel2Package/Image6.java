package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigRetangulo;
import Modelo.Matematica.Conta;

//retângulo
public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);
		int h = 4 + rand.nextInt(14);
		String strLado = "" + h;

		textLatex = "Image6" + h;

		resultadoCorreto = "" + h * (2 * h);

		pergunta="Se \\(h="+strLado+"\\), qual a área do retângulo?";
		
		resolucaoLatex="";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="b=2 \\cdot"+h+"="+(2*h)+"\\\\";
		resolucaoLatex+="A="+(2*h)+" \\cdot "+h+"="+(2*h*h);

		ConfigRetangulo config=new ConfigRetangulo("2h","h",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
