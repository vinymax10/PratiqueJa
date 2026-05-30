package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);

		int b = 50 + rand.nextInt(20);
		int c = 20 + rand.nextInt(20);
		int a = 180 - b - c;

//		4- ConfigTriangulo1 a, B, c | 
		ConfigTriangulo1 config = new ConfigTriangulo1(a, b, c);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.b.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + b + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", a, c);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
