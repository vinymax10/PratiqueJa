package Matematica.Basico.AreaPerimetro.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrapezio2;
import Matematica.Expressao.MyExpression;
import Modelo.Matematica.Conta;


public class Image17 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image17(int index)
	{
		super(index);

		int h = 2*(2 + rand.nextInt(8));
		int b = (int)(((double)h)*1.2);
		int B = (int)(((double)h)*1.8);

		String area = "" + (B + b) * h/2;
		
		textLatex = "Image17" + h + "-"+b+ "-"+B;

		resultadoCorreto = "" + h;
		pergunta="Se a área do trapézio é \\("+area+"\\), qual o valor de \\(h\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaTrapezio()+"\\\\";
		resolucaoLatex+="B="+B+", \\quad b="+b+"\\\\";
		resolucaoLatex+="\\dfrac{("+B+"+"+b+")\\cdot h"+"}{2} = "+area+"\\\\";
		MyExpression expressao = new MyExpression("("+B+"+"+b+") * h"+"="+area+"*2");
		resolucaoLatex+=expressao.resolverLatex();
		
		Config config = new ConfigTrapezio2(B+"", b+"", "h","",true);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image17(1);
	}
}
