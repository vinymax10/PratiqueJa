package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadrado;
import Modelo.Matematica.Conta;

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
