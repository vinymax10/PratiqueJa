package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloEquilatero;
import modelo.matematica.Conta;

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
