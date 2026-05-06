package matematica.intermediario.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.intermediario.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image9(int index)
	{
		super(index);

		int a = 50 + rand.nextInt(20);
		int c = 20 + rand.nextInt(20);
		int b = 180 - a - c;

//		9- ConfigTriangulo3 A, b, c | 
		ConfigTriangulo3 config = new ConfigTriangulo3(a, b, c);
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
		new Image9(1);
	}
}
