package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image11 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image11(int index)
	{
		super(index);

		int a = 50 + rand.nextInt(20);
		int c = 20 + rand.nextInt(20);
		int b = 180 - a - c;

//		11- ConfigTriangulo3 a, b, C | 
		ConfigTriangulo3 config = new ConfigTriangulo3(a, b, c);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.c.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + c + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", a, b);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image11(1);
	}
}
