package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import matematica.basico.somaangulostriangulo.nivel2package.ConfigRetangulo;
import modelo.matematica.Conta;


public class Image12 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image12(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(10);
		int d = 30 + rand.nextInt(10);
		int b = 90 - a;
		int c = 180 - b;
		int g = 90 - d;
		int e = b - d;
		int f = 90 - e - a;

//		12- ConfigRetangulo A, d, e | 
		ConfigRetangulo config = new ConfigRetangulo(a, b, c, d, e, f, g);
		config.a.mostrar();
		config.d.mostrar();
		config.e.mostrar();
		config.a.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + a + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", e, d, 90);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image12(1);
	}
}
