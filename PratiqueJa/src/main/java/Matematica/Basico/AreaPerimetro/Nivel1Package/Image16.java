package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrapezio;
import Modelo.Matematica.Conta;

public class Image16 extends Conta
{
	private static final long serialVersionUID = 1L;

//	trapézio
	public Image16(int index)
	{
		super(index);
		
		int B = 2*(3 + rand.nextInt(13));
		int b = (int)(((double)B)*0.65);
		int l = (int)(((double)B)*0.7);

		pergunta="Qual o perímetro do trapézio?";

		textLatex = "Image16" + l + "-" + b + "-" + B;
		resultadoCorreto = "" + (B+b+l+l);

		resolucaoLatex=ResolucaoAreaPerimetro.perimetroTrapezio(B, b,l,l);

		Config config=new ConfigTrapezio(B+"", b+"", "",""+l,""+l,false);
		
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image16(1);
	}
}
