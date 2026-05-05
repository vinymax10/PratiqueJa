package Matematica.Intermediario.SomaAngulosTriangulo.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


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

//		13- Retangulo B, e, g | a, f
		ConfigRetangulo config = new ConfigRetangulo(a, b, c, d, e, f, g);
		config.b.mostrar();
		config.e.mostrar();
		config.g.mostrar();
		config.b.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + b + "°";
		resolucaoLatex = resolucao(a, b, c, d, e, f, g);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.f.mostrar();
		config.f.nome = "y";

		config.a.mostrar();
		config.a.nome = "z";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e, int f, int g)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", g, 90);
		resolucaoLatex += ResolucaoSAT2.complemento90("z", f, e);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", a, 90);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image13(1);
	}
}
