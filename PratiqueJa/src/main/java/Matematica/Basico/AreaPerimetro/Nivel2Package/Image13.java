package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigRetangulo2;
import Modelo.Matematica.Conta;

//Retangulo com circunferencia dentro
public class Image13 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image13(int index)
	{
		super(index);
		int h = 2*(2 + rand.nextInt(9));
		int b = (int)(((double)h)*1.35);
		int raio=h/2;
		
		textLatex = "Image13" + b+" - "+h+" - "+raio;

		resultadoCorreto = "" + (2*(b+h));

		pergunta="Se \\(r="+raio+"\\), qual o perímetro do retângulo?";
		resolucaoLatex="h=2r= 2 \\cdot"+raio+"="+h+"\\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaPerimetroRetangulo()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+="P=2 \\cdot ("+b+"+"+h+")=2 \\cdot "+(b+h)+"="+(2*(b+h));
		
		ConfigRetangulo2 config=new ConfigRetangulo2(b+"","h","r",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image13(1);
	}
}
