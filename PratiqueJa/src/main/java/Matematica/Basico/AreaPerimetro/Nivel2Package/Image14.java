package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigRetangulo;
import Modelo.Matematica.Conta;

//retângulo
public class Image14 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image14(int index)
	{
		super(index);
		int h = 4 + rand.nextInt(14);
		String strLado = "" + h;

		textLatex = "Image14" + h;

		resultadoCorreto = "" + (2*((2*h)+h));

		pergunta="Se \\(h="+strLado+"\\), qual o perímetro do retângulo?";
		
		resolucaoLatex="";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaPerimetroRetangulo()+"\\\\";
		resolucaoLatex+="b=2 \\cdot"+h+"="+(2*h)+"\\\\";
		resolucaoLatex+="P=2 \\cdot ("+(2*h)+"+"+h+")=2 \\cdot "+((2*h)+h)+"="+(2*((2*h)+h));

		ConfigRetangulo config=new ConfigRetangulo("2h","h",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image14(1);
	}
}
