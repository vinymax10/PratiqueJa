package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;
import modelo.matematica.Conta;

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
