package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloIsosceles;
import Modelo.Matematica.Conta;


//	triângulo
public class Image14 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image14(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(13));
		int a = (int)(((double)b)*0.7);//altura
		int c=a;
		
		pergunta="Qual o perímetro do triângulo?";
		textLatex = "Image14" + b + "-" + a+ "-" + c;

		resultadoCorreto = "" + (b + a +c);

		resolucaoLatex=ResolucaoAreaPerimetro.perimetroTrianguloIsosceles(a, b);
		
		Config config=new ConfigTrianguloIsosceles(""+b,"","",""+a,false);
		
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image14(1);
	}
}
