package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo3;
import modelo.matematica.Conta;

//Retangulo com quadrado dentro
public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);
		
		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		int sobra=b-h;
		textLatex = "Image3" + b+" - "+h+" - "+sobra;

		String strD = "" + h + "\\sqrt{2}";
		resultadoCorreto = "" + h * (h + sobra);
		
		pergunta="Se \\(d="+strD+"\\), qual a área do retângulo?";
		resolucaoLatex=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+
		", \\quad "+ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="l="+h+"\\\\";
		resolucaoLatex+="h="+h+"\\\\";
		resolucaoLatex+="b=l+"+sobra+" = "+h+"+"+sobra+"="+(h+sobra) +"\\\\";
		resolucaoLatex+="A="+b+" \\cdot "+h+"="+(b*h);

		ConfigRetangulo3 config=new ConfigRetangulo3("d","" + sobra,true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image3(1);
	}
}
