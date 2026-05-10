package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloRetangulo;
import modelo.matematica.Conta;


public class Image20 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image20(int index)
	{
		super(index);
		
		int b = 2*(5 + rand.nextInt(13));
		int a = (int)(((double)b)*0.6);//altura
		int c = (int)(((double)b)*1.2);//hipotenusa
		
		String perimetro = "" + (b + a +c);
		
		textLatex = "Image39" + c + "-" + b + "-" + a;

		resultadoCorreto = "" + a;
		pergunta="Se o perímetro do triângulo é \\("+perimetro+"\\), qual o valor de \\(a\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTriangulo()+"\\\\";
		resolucaoLatex+="b="+b+",\\quad c="+c+"\\\\";
		resolucaoLatex+="a+"+b+"+"+c+"="+perimetro+"\\\\";
		resolucaoLatex+="a="+perimetro+"-"+b+"-"+c+"="+a;
		
		Config config = new ConfigTrianguloRetangulo(""+b, "a", ""+c,false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image20(1);
	}
}
