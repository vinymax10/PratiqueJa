package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTriangulo;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;
import matematica.basico.areaperimetro.config.ConfigTrianguloRetangulo;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;

public class Image20 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image20(int index)
	{
		super(index);

		int b = 2*(3 + rand.nextInt(13));
		int h = (int)(((double)b)*0.6);
		
		String area = "" + h * b / 2;
		
		resultadoCorreto = h+"";
		pergunta="Se a área do triângulo é \\("+area+"\\), qual o valor de \\(h\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaAreaTriangulo()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+="\\dfrac{"+b +"\\cdot h}{2} = "+area+"\\\\";
		MyExpression expressao = new MyExpression(b+" * h="+area+"*2");
		resolucaoLatex+=expressao.resolverLatex();
		
		Config config=null;
		
		int num=rand.nextInt(3);
		switch(num)
		{
			case 0: config=new ConfigTriangulo(b+"","h","","",true);break;
			case 1: config=new ConfigTrianguloRetangulo(b+"","h","",true);break;
			case 2: config=new ConfigTrianguloIsosceles(b+"","h","","",true);break;
		}
		
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image20(1);
	}
}
