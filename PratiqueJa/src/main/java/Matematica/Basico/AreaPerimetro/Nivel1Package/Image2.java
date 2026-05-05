package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigRetangulo;
import Modelo.Matematica.Conta;

//	retângulo
public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;
	
	public Image2(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		pergunta="Qual a área do retângulo?";

		textLatex = "Image2" + b + h;
		resultadoCorreto = "" + (b * h);
		resolucaoLatex=ResolucaoAreaPerimetro.areaRetangulo(b, h);

		ConfigRetangulo config=new ConfigRetangulo(""+b,""+h,true);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}

}
