package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;
import modelo.matematica.Conta;

//	Quadrado
public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		int l = 3 + rand.nextInt(20);
		
		pergunta="Qual a área do quadrado?";
		textLatex = "Image1" + l;
		resultadoCorreto = "" + (l * l);
		
		resolucaoLatex=ResolucaoAreaPerimetro.areaQuadrado(l);
		
		ConfigQuadrado config=new ConfigQuadrado(""+l,"",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
//		Graphics.salvar(image, true, "image.PNG");
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}

}
