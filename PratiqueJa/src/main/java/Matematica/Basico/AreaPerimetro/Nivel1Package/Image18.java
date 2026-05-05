package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigLozango;
import Modelo.Matematica.Conta;


public class Image18 extends Conta
{
	private static final long serialVersionUID = 1L;

//	lozango
	public Image18(int index)
	{
		super(index);

		int l = 3 + rand.nextInt(20);

		
		pergunta="Qual o perímetro do losango?";

		textLatex = "Image18" + l;
		resultadoCorreto = "" + (4 * l);
		
		resolucaoLatex=ResolucaoAreaPerimetro.perimetroQuadrado(l);
		
		ConfigLozango config=new ConfigLozango("", "","","",""+l,false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image18(1);
	}
}