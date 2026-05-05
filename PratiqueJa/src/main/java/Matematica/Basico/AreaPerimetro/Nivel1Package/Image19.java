package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigCircunferencia;
import Modelo.Matematica.Conta;

//	Quadrado
public class Image19 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image19(int index)
	{
		super(index);

		int r = (3 + rand.nextInt(20));

		pergunta="Se o comprimento da circunferência é \\(C=x\\pi\\), qual o valor de \\(x\\)?";
		resultadoCorreto = "" + (2 * r);
		textLatex = "Image19" + r;

		resolucaoLatex=ResolucaoAreaPerimetro.comprimentoCircunferencia(r);

		ConfigCircunferencia config=new ConfigCircunferencia(""+r,false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image19(1);
	}

}
