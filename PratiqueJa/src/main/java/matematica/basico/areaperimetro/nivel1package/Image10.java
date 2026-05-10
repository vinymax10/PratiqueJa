package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;
import modelo.matematica.Conta;

//	retângulo
public class Image10 extends Conta
{
	private static final long serialVersionUID = 1L;
	
	public Image10(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(12));
		int h = (int)(((double)b)*0.7);
		
		pergunta="Qual o perímetro do retângulo?";

		textLatex = "Image10" + b + h;
		resultadoCorreto = "" + (2* (b + h));
		resolucaoLatex=ResolucaoAreaPerimetro.perimetroRetangulo(b, h);

		ConfigRetangulo config=new ConfigRetangulo(""+b,""+h,false);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image10(1);
	}

}
