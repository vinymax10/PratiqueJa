package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadrado;
import Modelo.Matematica.Conta;

//	Perimetro Quadrado
public class Image9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image9(int index)
	{
		super(index);

		int l = 3 + rand.nextInt(20);
		
		pergunta="Qual o perímetro do quadrado?";
		textLatex = "Image9" + l;
		resultadoCorreto = "" + (4 * l);
		
		resolucaoLatex=ResolucaoAreaPerimetro.perimetroQuadrado(l);
		
		ConfigQuadrado config=new ConfigQuadrado(""+l,"",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image9(1);
	}

}
