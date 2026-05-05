package Matematica.Basico.AreaPerimetro.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.AreaPerimetro.ResolucaoAreaPerimetro;
import Matematica.Basico.AreaPerimetro.Config.ConfigQuadrado;
import Modelo.Matematica.Conta;

//Quadrado com lateralEsq
public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		int l = 3 + rand.nextInt(20);

		String strDiagonal = "" + l + "\\sqrt{2}";

		pergunta="Se \\(d="+strDiagonal+"\\), qual a área do quadrado?";

		textLatex = "Image1" + l;
		resultadoCorreto = "" + l * l;
		
		resolucaoLatex=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+
		", \\quad "+ResolucaoAreaPerimetro.formulaAreaQuadrado()+"\\\\";
		resolucaoLatex+="l="+l+"\\\\";
		resolucaoLatex+="A="+l+"^2="+l+" \\cdot "+l+"="+(l*l);

		ConfigQuadrado config=new ConfigQuadrado("l","d",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}


}
