package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo2;
import modelo.matematica.Conta;

//Retangulo com circunferencia dentro
public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);
		int h = 2*(2 + rand.nextInt(9));
		int b = (int)(((double)h)*1.35);
		int raio=h/2;
		
		textLatex = "Image5" + b+" - "+h+" - "+raio;

		resultadoCorreto = "" + (b*h);

		pergunta="Se \\(r="+raio+"\\), qual a área do retângulo?";
		resolucaoLatex="h=2r= 2 \\cdot"+raio+"="+h+"\\\\";
		resolucaoLatex+=ResolucaoAreaPerimetro.formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="b="+b+"\\\\";
		resolucaoLatex+="A="+b+" \\cdot "+h+"="+(b*h);
		
		ConfigRetangulo2 config=new ConfigRetangulo2(b+"","h","r",true);
		BufferedImage image=config.criarImagem(index);
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
