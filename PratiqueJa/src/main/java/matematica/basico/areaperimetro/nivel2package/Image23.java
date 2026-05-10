package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadradoCircunferencia;
import modelo.matematica.Conta;


public class Image23 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image23(int index)
	{
		super(index);

		int a = 2 + rand.nextInt(18);

		textLatex = "Image23" + a + "";
		String perimetro = "" + (4 * a) +"\\sqrt{2}";
		
		resultadoCorreto = "" + a;
		pergunta="Se o perímetro do quadrado é \\("+perimetro+"\\), qual o valor de \\(r\\)?";

		resolucaoLatex=ResolucaoAreaPerimetro.formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="4 \\cdot l="+perimetro+"\\\\";
		resolucaoLatex+="l="+"\\dfrac{"+perimetro+"}{4}="+a+" \\sqrt{2} \\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaDiagonalQuadrado()+", \\quad ";
		resolucaoLatex+="d=2r\\\\";
		resolucaoLatex+="2r=l\\sqrt{2}\\\\";
		resolucaoLatex+="2r="+a+"\\sqrt{2} \\cdot \\sqrt{2}\\\\";
		resolucaoLatex+="2r="+a+"(\\sqrt{2})^2 = "+a+"\\cdot 2 ="+(a * 2)+"\\\\";
		resolucaoLatex+="r=\\dfrac{"+(a * 2)+"}{2} = "+a+"\\\\";

		ConfigQuadradoCircunferencia config=new ConfigQuadradoCircunferencia("r","l",false);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image23(1);
	}
}
