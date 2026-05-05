package Matematica.Intermediario.SomaAngulosTriangulo.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


public class Image35 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image35(int index)
	{
		super(index);

		int a = 40 + rand.nextInt(10);
		int b = 50 + rand.nextInt(10);
		int e = 40 + rand.nextInt(10);
		int c = 180 - a - b;
		int d = 180 - c;
		int f = 180 - b - e - a;

//		35- TrianguloBipartido2 a, b, D | c
		ConfigTrianguloBipartido2 config = new ConfigTrianguloBipartido2(a, b, c, d, e, f);
		config.a.mostrar();
		config.b.mostrar();
		config.d.mostrar();
		config.d.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + d + "°";
		resolucaoLatex = resolucao(a, b, c, d, e, f);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.c.mostrar();
		config.c.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e, int f)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", a, b);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", c);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image35(1);
	}
}
