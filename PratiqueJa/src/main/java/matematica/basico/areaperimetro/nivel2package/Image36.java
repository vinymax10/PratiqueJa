package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTriangulo;
import modelo.matematica.Conta;


public class Image36 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image36(int index)
	{
		super(index);
		
		int b = 2*(3 + rand.nextInt(13));
		int a = (int)(((double)b)*0.6);
		int c = (int)(((double)b)*0.85);
		
		String perimetro = "" + (b + a +c);
		
		textLatex = "Image36" + c + "-" + b + "-" + a;

		resultadoCorreto = "" + a;
		pergunta="Se o perímetro do triângulo é \\("+perimetro+"\\), qual o valor de \\(a\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroTriangulo()+"\\\\";
		resolucaoLatex+="b="+b+",\\quad c="+c+"\\\\";
		resolucaoLatex+="a+"+b+"+"+c+"="+perimetro+"\\\\";
		resolucaoLatex+="a="+perimetro+"-"+b+"-"+c+"="+a;
		
		Config config = new ConfigTriangulo(""+b, "", "a", ""+c,false);
		BufferedImage image = config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image36(1);
	}
}
