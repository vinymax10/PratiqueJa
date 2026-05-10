package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadradoCircunferencia;
import modelo.matematica.Conta;

//Quadrado inscrito na circunferência
public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int raio = 1 + rand.nextInt(10);

		String strRaio = raio + "\\sqrt{2}";
		textLatex = "Image2" + raio;

		pergunta="Se \\(r="+strRaio+"\\), qual a área do quadrado?";

		resultadoCorreto = "" + (2 * raio) * (2 * raio);

		int l=(2*raio);
		
		resolucaoLatex="";
		resolucaoLatex+="\\text{Seja~} d \\text{~a diagonal do quadrado}\\\\";
		resolucaoLatex+="d=2r = 2 \\cdot"+raio+"\\sqrt{2} = "+(2*raio)+"\\sqrt{2}\\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+""
		+ ",\\quad"+ResolucaoAreaPerimetro.formulaAreaQuadrado()+"\\\\";
		resolucaoLatex+="l="+(2*raio)+"\\\\";
		resolucaoLatex+="A="+l+"^2="+l+" \\cdot "+l+"="+(l*l);

		ConfigQuadradoCircunferencia config=new ConfigQuadradoCircunferencia("r","l",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
