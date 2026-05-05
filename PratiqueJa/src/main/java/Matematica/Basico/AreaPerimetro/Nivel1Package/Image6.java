package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrapezio2;
import Modelo.Matematica.Conta;

public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

//	trapézio
	public Image6(int index)
	{
		super(index);

		int h = 2*(2 + rand.nextInt(8));
		int b = (int)(((double)h)*1.2);
		int B = (int)(((double)h)*1.8);

		pergunta="Qual a área do trapézio?";

		textLatex = "Image6" + h + "-" + b + "-" + B;
		resultadoCorreto = "" + (((B + b) * h) / 2);

		resolucaoLatex=ResolucaoAreaPerimetro.areaTrapezio(h, B, b);

		Config config=new ConfigTrapezio2(B+"", b+"", h+"","",true);
		
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
