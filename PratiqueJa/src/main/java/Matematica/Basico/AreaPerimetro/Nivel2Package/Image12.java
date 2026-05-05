package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadrado3;
import Modelo.Matematica.Conta;

//Quadrado com circunferencia dentro
public class Image12 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image12(int index)
	{
		super(index);
		int raio = 1 + rand.nextInt(10);
		String strRaio = "" + raio;
		textLatex = "Image12" +raio;

		pergunta="Se \\(r="+strRaio+"\\), qual o perímetro do quadrado?";
		int l=2*raio;

		resultadoCorreto = "" + (4*l);
		resolucaoLatex="l=2r= 2 \\cdot"+raio+"="+l+"\\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="P=4 \\cdot "+l+"="+(4*l);
		
		ConfigQuadrado3 config=new ConfigQuadrado3("l","r",false);
		BufferedImage image=config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image12(1);
	}
}
