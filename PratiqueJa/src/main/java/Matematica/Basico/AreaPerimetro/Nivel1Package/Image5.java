package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrapezio;
import Modelo.Matematica.Conta;

public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

//	trapézio
	public Image5(int index)
	{
		super(index);
		
		int h = 2*(2 + rand.nextInt(8));
		int b = (int)(((double)h)*1.2);
		int B = (int)(((double)h)*2.1);

		pergunta="Qual a área do trapézio?";

		textLatex = "Image5" + h + "-" + b + "-" + B;
		resultadoCorreto = "" + (((B + b) * h) / 2);

		resolucaoLatex=ResolucaoAreaPerimetro.areaTrapezio(h, B, b);

		Config config=new ConfigTrapezio(B+"", b+"", h+"","","",true);
		
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
