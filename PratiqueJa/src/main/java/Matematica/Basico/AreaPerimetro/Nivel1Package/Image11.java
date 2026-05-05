package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigParalelogramo;
import Modelo.Matematica.Conta;

//	perímetro do paralelogramo
public class Image11 extends Conta
{
	private static final long serialVersionUID = 1L;
	
	public Image11(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int a = (int)(((double)b)*0.75);
		
		pergunta="Qual o perímetro do paralelogramo?";

		textLatex = "Image11" + b + "-" + a;
		resultadoCorreto = "" + (2* (b + a));
		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroParalelogramo(a,b);

		ConfigParalelogramo config=new ConfigParalelogramo("" +b,"h",""+a,false);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image11(1);
	}

}
