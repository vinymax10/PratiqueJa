package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;
import modelo.matematica.Conta;

//	triangulo isosceles
public class Image15 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image15(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(13));
		int a = (int)(((double)b)*0.7);//altura
		
		int meiaBase=b/2;
		
		textLatex = "Image15" + b+" - "+a+" - "+meiaBase;

		pergunta="Qual o perímetro do triângulo?";

		resultadoCorreto = "" + ((2*a)+b);

		resolucaoLatex="";		
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaPerimetroTrianguloIsosceles()+"\\\\";
		resolucaoLatex+="a="+a+", \\quad b="+meiaBase+" \\cdot 2="+b+"\\\\";
		resolucaoLatex+="P=2 \\cdot "+a+"+"+b+"="+(2*a)+"+"+b+"="+((2*a)+b);
		
		Config config=new ConfigTrianguloIsosceles(""," ",""+meiaBase,""+a,false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image15(1);
	}

}
