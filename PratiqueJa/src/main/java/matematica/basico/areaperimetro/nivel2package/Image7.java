package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;
import modelo.matematica.Conta;

//	triangulo isosceles
public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image7(int index)
	{
		super(index);

		int b = 2*(5 + rand.nextInt(16));
		int h = (int)(((double)b)*0.6);
		
		int meiaBase=b/2;
		
		textLatex = "Image7" + b+" - "+h+" - "+meiaBase;

		String strAltura = "" + h;

		pergunta="Qual a área do triângulo?";

		resultadoCorreto = "" + (b * h)/2;

		resolucaoLatex="";		
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaAreaTriangulo()+"\\\\";
		resolucaoLatex+="h="+h+", \\quad b="+meiaBase+" \\cdot 2="+b+"\\\\";
		resolucaoLatex+="A=\\dfrac{"+b+" \\cdot "+h+"}{2}=";
		resolucaoLatex+="\\dfrac{"+b*h+"}{2}=";
		resolucaoLatex+=(b*h)/2;
		
		Config config=new ConfigTrianguloIsosceles("",strAltura,""+meiaBase,"",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image7(1);
	}

}
