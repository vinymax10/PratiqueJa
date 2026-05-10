package matematica.intermediario.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);

		int b = 20 + rand.nextInt(10);
		int a = 20 + rand.nextInt(10);
		int c = 180 - b - a;

//		6- ConfigTriangulo2 A, b, c | 
		ConfigTriangulo2 config = new ConfigTriangulo2(a, b, c);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.a.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + a + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", b, c);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
