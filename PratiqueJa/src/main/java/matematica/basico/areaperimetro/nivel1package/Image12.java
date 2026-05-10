package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTriangulo;
import modelo.matematica.Conta;


//	triângulo
public class Image12 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image12(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(13));
		int a = (int)(((double)b)*0.6);
		int c = (int)(((double)b)*0.85);
		
		pergunta="Qual o perímetro do triângulo?";
		textLatex = "Image12" + b + "-" + a+ "-" + c;

		resultadoCorreto = "" + (b + a +c);

		resolucaoLatex=ResolucaoAreaPerimetro.perimetroTriangulo(a, b, c);
		
		Config config=new ConfigTriangulo(""+b,"h",""+a,""+c,false);
		
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image12(1);
	}
}
