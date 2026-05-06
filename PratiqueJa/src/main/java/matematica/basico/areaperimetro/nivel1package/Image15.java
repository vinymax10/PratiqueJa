package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloEquilatero;
import modelo.matematica.Conta;


//	triângulo
public class Image15 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image15(int index)
	{
		super(index);

		int l = 2*(3 + rand.nextInt(13));
		
		pergunta="Qual o perímetro do triângulo?";
		textLatex = "Image15" + l + "-" + l+ "-" + l;

		resultadoCorreto = "" + (3*l);

		resolucaoLatex=ResolucaoAreaPerimetro.perimetroTrianguloEquilatero(l);
		
		Config config=new ConfigTrianguloEquilatero(""+l,false);
		
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image15(1);
	}
}
