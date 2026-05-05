package Matematica.Intermediario.SomaAngulosTriangulo.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		int b = 60 + rand.nextInt(20);
		int e = 40 + rand.nextInt(20);
		int d = 90 - e;
		int c = d;
		int a = 180 - b - d;

//		1- Trapezio A, b, c | d, e
		ConfigTrapezio config = new ConfigTrapezio(a, b, c, d, e);
		config.b.mostrar();
		config.c.mostrar();
		config.a.mostrar();
		config.a.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + a + "°";
		resolucaoLatex = resolucao(a, b, c, d, e);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.d.mostrar();
		config.d.nome = "y";

		config.e.mostrar();
		config.e.nome = "z";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("z", c, 90);
		resolucaoLatex += ResolucaoSAT2.complemento90("y", e);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", b, d);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
