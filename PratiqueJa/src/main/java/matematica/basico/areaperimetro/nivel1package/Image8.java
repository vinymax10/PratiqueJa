package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;
import modelo.matematica.Conta;

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
