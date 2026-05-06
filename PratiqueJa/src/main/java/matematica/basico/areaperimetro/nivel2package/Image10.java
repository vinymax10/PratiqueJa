package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadradoCircunferencia;
import modelo.matematica.Conta;

//Quadrado inscrito na circunferência
public class Image10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image10(int index)
	{
		super(index);

		int raio = 1 + rand.nextInt(10);

		String strRaio = raio + "\\sqrt{2}";
		textLatex = "Image10" + raio;

		pergunta="Se \\(r="+strRaio+"\\), qual o perímetro do quadrado?";

		int l=(2*raio);
		resultadoCorreto = "" + (4*l);
		
		resolucaoLatex="";
		resolucaoLatex+="\\text{Seja~} d \\text{~a diagonal do quadrado}\\\\";
		resolucaoLatex+="d=2r = 2 \\cdot"+raio+"\\sqrt{2} = "+(2*raio)+"\\sqrt{2}\\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+""
		+ ",\\quad"+ResolucaoAreaPerimetro.formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="l="+(2*raio)+"\\\\";
		resolucaoLatex+="P=4 \\cdot "+l+"="+(4*l);

		ConfigQuadradoCircunferencia config=new ConfigQuadradoCircunferencia("r","l",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image10(1);
	}
}
