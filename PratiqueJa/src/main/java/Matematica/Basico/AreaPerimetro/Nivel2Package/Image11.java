package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigRetangulo3;
import Modelo.Matematica.Conta;

//Retangulo com quadrado dentro
public class Image11 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image11(int index)
	{
		super(index);
		
		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		int sobra=b-h;
		textLatex = "Image11" + b+" - "+h+" - "+sobra;

		String strD = "" + h + "\\sqrt{2}";
		resultadoCorreto = "" + (2*(b+h));
		
		pergunta="Se \\(d="+strD+"\\), qual o perímetro do retângulo?";
		resolucaoLatex=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+
		", \\quad "+ResolucaoAreaPerimetro.formulaPerimetroRetangulo()+"\\\\";
		resolucaoLatex+="l="+h+"\\\\";
		resolucaoLatex+="h="+h+"\\\\";
		resolucaoLatex+="b=l+"+sobra+" = "+h+"+"+sobra+"="+(h+sobra) +"\\\\";
		resolucaoLatex+="P=2 \\cdot ("+b+" + "+h+")=2 \\cdot "+(b+h) +"="+ (2*(b+h));

		ConfigRetangulo3 config=new ConfigRetangulo3("d","" + sobra,false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image11(1);
	}
}
