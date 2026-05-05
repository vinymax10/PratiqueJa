package Matematica.Basico.AreaPerimetro.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTriangulo;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloIsosceles;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloRetangulo;
import Modelo.Matematica.Conta;


//	triângulo
public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(13));
		int h = (int)(((double)b)*0.6);
		
		pergunta="Qual a área do triângulo?";
		textLatex = "Image4" + b + "-" + h;

		resultadoCorreto = "" + ((b * h)/2);

		resolucaoLatex=ResolucaoAreaPerimetro.areaTriangulo(b, h);
		
		Config config=null;
		
		int num=rand.nextInt(3);
		switch(num)
		{
			case 0: config=new ConfigTriangulo(""+b,""+h,"","",true);break;
			case 1: config=new ConfigTrianguloRetangulo(""+b, ""+h,"",true);break;
			case 2: config=new ConfigTrianguloIsosceles(""+b,""+h,"","",true);break;
		}

		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
