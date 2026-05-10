package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;
import modelo.matematica.Conta;


public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

//	lozango
	public Image7(int index)
	{
		super(index);

		int D = 2*(3 + rand.nextInt(13));
		int d = (int)(((double)D)*0.7);
		
		pergunta="Qual a área do losango?";

		textLatex = "Image7" + D + "-" + d;
		resultadoCorreto = "" + (D/2 * d);
		
		resolucaoLatex=ResolucaoAreaPerimetro.losango(D, d);
		
		ConfigLozango config=new ConfigLozango(""+D, ""+d,"","","",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image7(1);
	}
}