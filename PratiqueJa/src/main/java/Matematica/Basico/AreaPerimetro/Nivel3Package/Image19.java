package Matematica.Basico.AreaPerimetro.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTriangulo;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloIsosceles;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloRetangulo;
import Matematica.Expressao.MyExpression;
import Modelo.Matematica.Conta;


public class Image19 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image19(int index)
	{
		super(index);
		
		int b = 2*(3 + rand.nextInt(13));
		int h = (int)(((double)b)*0.6);
		
		String area = "" + h * b / 2;
		resultadoCorreto = "" + b;
		
		textLatex = "Image19" + h + "-"+b;

		pergunta="Se a área do triângulo é \\("+area+"\\), qual o valor de \\(b\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaTriangulo()+"\\\\";
		resolucaoLatex+="h="+h+"\\\\";
		resolucaoLatex+="\\dfrac{b \\cdot "+h+"}{2} = "+area+"\\\\";
		MyExpression expressao = new MyExpression("b * "+h+"="+area+"*2");
		resolucaoLatex+=expressao.resolverLatex();
		
		Config config=null;
		
		int num=rand.nextInt(3);
		switch(num)
		{
			case 0: config=new ConfigTriangulo("b",h+"","","",true);break;
			case 1: config=new ConfigTrianguloRetangulo("b",h+"","",true);break;
			case 2: config=new ConfigTrianguloIsosceles("b",h+"","","",true);break;
		}
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image19(1);
	}

}
