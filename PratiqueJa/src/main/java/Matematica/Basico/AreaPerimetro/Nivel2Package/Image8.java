package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.Config;
import Matematica.Basico.AreaPerimetro.Config.ConfigTrianguloEquilatero;
import Modelo.Matematica.Conta;

//	triangulo isosceles
public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int index)
	{
		super(index);

		int l = 2*(2 + rand.nextInt(10));
		
		pergunta="Se a área do triângulo é \\(A=x\\sqrt{3}\\), qual o valor de \\(x\\)?";

		textLatex = "Image8" + l + "-" + l;
		
		resultadoCorreto = "" + (l*l)/4;

		resolucaoLatex="";		
		resolucaoLatex+=ResolucaoAreaPerimetro.areaTrianguloEquilatero(l);
		
		Config config=new ConfigTrianguloEquilatero(""+l,true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}

}
