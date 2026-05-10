package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigParalelogramo;
import modelo.matematica.Conta;


//	paralelogramo
public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		pergunta="Qual a área do paralelogramo?";

		textLatex = "Image3" + b + "-" + h;
		resultadoCorreto = "" + (b * h);
		resolucaoLatex=ResolucaoAreaPerimetro.areaRetangulo(b, h);

		ConfigParalelogramo config=new ConfigParalelogramo("" +b,"" +h,"",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image3(1);
	}
}
