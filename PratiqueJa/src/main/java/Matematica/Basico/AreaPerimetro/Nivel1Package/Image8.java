package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigCircunferencia;
import Modelo.Matematica.Conta;

//	Quadrado
public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int index)
	{
		super(index);

		int r = (3 + rand.nextInt(20));

		pergunta="Se a área do círculo é \\(A=x\\pi\\), qual o valor de \\(x\\)?";
		textLatex = "Image8" + r;
		resultadoCorreto = "" + (r * r);

		resolucaoLatex=ResolucaoAreaPerimetro.areaCirculo(r);

		ConfigCircunferencia config=new ConfigCircunferencia(""+r,true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}

}
