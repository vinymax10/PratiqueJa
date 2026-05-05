package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrapezio2;
import Modelo.Matematica.Conta;

public class Image17 extends Conta
{
	private static final long serialVersionUID = 1L;

//	trapézio
	public Image17(int index)
	{
		super(index);
		
		int B = 2*(3 + rand.nextInt(13));
		int b = (int)(((double)B)*0.65);
		int h = (int)(((double)B)*0.55);
		int l = (int)(((double)B)*0.7);

		pergunta="Qual o perímetro do trapézio?";

		textLatex = "Image17" + l + "-" + b + "-" + B+" - "+h;
		resultadoCorreto = "" + (B+b+h+l);

		resolucaoLatex=ResolucaoAreaPerimetro.perimetroTrapezio(B, b,h,l);

		Config config=new ConfigTrapezio2(B+"", b+"", ""+h,""+l,false);
		
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image17(1);
	}
}
