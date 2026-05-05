package Matematica.Intermediario.SomaAngulosTriangulo.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image7(int index)
	{
		super(index);

		int b = 55 + rand.nextInt(15);
		int e = 10 + rand.nextInt(15);
		int f = 25 + rand.nextInt(15);
		int c = e + f;
		int a = 180 - b - c;
		int d = 180 - c;

//		7- TrianguloBipartido1 C, e, f | d
		ConfigTrianguloBipartido1 config = new ConfigTrianguloBipartido1(a, b, c, d, e, f);
		config.c.mostrar();
		config.e.mostrar();
		config.f.mostrar();
		config.c.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + c + "°";
		resolucaoLatex = resolucao(a, b, c, d, e, f);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.d.mostrar();
		config.d.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e, int f)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", e, f);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", d);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image7(1);
	}
}
