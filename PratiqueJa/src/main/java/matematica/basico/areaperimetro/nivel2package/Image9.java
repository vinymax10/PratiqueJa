package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;
import modelo.matematica.Conta;

//Quadrado com lateralEsq
public class Image9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image9(int index)
	{
		super(index);

		int l = 3 + rand.nextInt(20);

		String strDiagonal = "" + l + "\\sqrt{2}";

		pergunta="Se \\(d="+strDiagonal+"\\), qual o perímetro do quadrado?";

		textLatex = "Image9" + l;
		resultadoCorreto = "" + (4 * l);
		
		resolucaoLatex=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+
		", \\quad "+ResolucaoAreaPerimetro.formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="l="+l+"\\\\";
		resolucaoLatex+="P=4 \\cdot "+l+"="+(4*l);

		ConfigQuadrado config=new ConfigQuadrado("l","d",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}


	public static void main(String[] args)
	{
		new Image9(1);
	}


}
