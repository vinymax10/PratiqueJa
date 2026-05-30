package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import matematica.basico.somaangulostriangulo.nivel2package.ConfigRetangulo;
import modelo.matematica.Conta;


public class Image13 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image13(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(10);
		int d = 30 + rand.nextInt(10);
		int b = 90 - a;
		int c = 180 - b;
		int g = 90 - d;
		int e = b - d;
		int f = 90 - e - a;

//		13- ConfigRetangulo a, D, e | 
		ConfigRetangulo config = new ConfigRetangulo(a, b, c, d, e, f, g);
		config.a.mostrar();
		config.d.mostrar();
		config.e.mostrar();
		config.d.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + d + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", a, e, 90);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image13(1);
	}
}
