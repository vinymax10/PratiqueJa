package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;
import modelo.matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int a = 1 + rand.nextInt(10);

		String area = "" + 2 * a * a;
		resultadoCorreto = "" + a * 2;
		
		textLatex = "Image2" + a;

		pergunta="Se a área do quadrado é \\("+area+"\\), qual o valor de \\(d\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaQuadrado()+"\\\\";
		resolucaoLatex+="l^2="+area+"\\\\";
		resolucaoLatex+="l="+"\\sqrt{"+area+"} = \\sqrt{"+a+"^2 \\cdot 2} = "+a+"\\sqrt{2}\\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+"\\\\";
		resolucaoLatex+="d="+a+"\\sqrt{2} \\cdot \\sqrt{2}\\\\";
		resolucaoLatex+="d="+a+"(\\sqrt{2})^2 = "+a+"\\cdot 2 ="+(a * 2)+"\\\\";

		ConfigQuadrado config=new ConfigQuadrado("l","d",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
