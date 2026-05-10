package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloRetangulo;
import modelo.matematica.Conta;


//	triângulo
public class Image13 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image13(int index)
	{
		super(index);

		int b = 2*(5 + rand.nextInt(13));
		int a = (int)(((double)b)*0.6);//altura
		int c = (int)(((double)b)*1.2);//hipotenusa
		
		pergunta="Qual o perímetro do triângulo?";
		textLatex = "Image13" + b + "-" + a+ "-" + c;

		resultadoCorreto = "" + (b + a +c);

		resolucaoLatex=ResolucaoAreaPerimetro.perimetroTriangulo(a, b, c);
		
		Config config=new ConfigTrianguloRetangulo(""+b,""+a,""+c,false);
		
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image13(1);
	}
}
