package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;
import modelo.matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		int l = 3 + rand.nextInt(20);

		String area = "" + l * l;
		resultadoCorreto = "" + l;
		textLatex = "Image1" + l + "";

		pergunta="Se a área do quadrado é \\("+area+"\\), qual o valor de \\(l\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaQuadrado()+"\\\\";
		resolucaoLatex+="l^2="+area+"\\\\";
		resolucaoLatex+="l="+"\\sqrt{"+area+"} = "+l+"\\\\";

		ConfigQuadrado config=new ConfigQuadrado("l","",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
