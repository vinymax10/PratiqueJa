package Matematica.Basico.AreaPerimetro.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigLozango;
import Matematica.Expressao.MyExpression;
import Modelo.Matematica.Conta;


public class Image12 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image12(int index)
	{
		super(index);

		int D = 2*(3 + rand.nextInt(13));
		int d = (int)(((double)D)*0.7);
		
		String area = "" + D/2 * d;
		
		textLatex = "Image12" + D + "-"+d;

		resultadoCorreto = D+"";
		pergunta="Se a área do lozango é \\("+area+"\\), qual o valor de \\(D\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaLosango()+"\\\\";
		resolucaoLatex+="d="+d+"\\\\";
		resolucaoLatex+="\\dfrac{D \\cdot "+d+"}{2} = "+area+"\\\\";
		MyExpression expressao = new MyExpression("D * "+d+"="+area+"*2");
		resolucaoLatex+=expressao.resolverLatex();
		
		ConfigLozango config=new ConfigLozango("D", d+"","","","",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image12(1);
	}
}
