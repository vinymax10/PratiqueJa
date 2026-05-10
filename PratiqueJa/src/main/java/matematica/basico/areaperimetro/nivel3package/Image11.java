package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image11 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image11(int index)
	{
		super(index);

		int D = 2*(3 + rand.nextInt(13));
		int d = (int)(((double)D)*0.7);
		
		String area = "" + D/2 * d;
		
		textLatex = "Image11" + D + "-"+d;
		
		resultadoCorreto = d+"";
		pergunta="Se a área do lozango é \\("+area+"\\), qual o valor de \\(d\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaLosango()+"\\\\";
		resolucaoLatex+="D="+D+"\\\\";
		resolucaoLatex+="\\dfrac{"+D+" \\cdot d }{2} = "+area+"\\\\";
		MyExpression expressao = new MyExpression(" "+D+" * d ="+area+"*2");
		resolucaoLatex+=expressao.resolverLatex();
		
		ConfigLozango config=new ConfigLozango(D+"", "d","","","",true);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image11(1);
	}
}
