package Matematica.Intermediario.SomaAngulosTriangulo.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


public class Image27 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image27(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(10);
		int d = 30 + rand.nextInt(10);
		int b = 90 - a;
		int c = 180 - b;
		int g = 90 - d;
		int e = b - d;
		int f = 90 - e - a;

//		27- Retangulo D, f | g
		ConfigRetangulo config = new ConfigRetangulo(a, b, c, d, e, f, g);
		config.d.mostrar();
		config.f.mostrar();
		config.d.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + d + "°";
		resolucaoLatex = resolucao(a, b, c, d, e, f, g);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.g.mostrar();
		config.g.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e, int f, int g)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", f, 90);
		resolucaoLatex += ResolucaoSAT2.complemento90("x", g);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image27(1);
	}
}
