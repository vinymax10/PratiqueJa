package Matematica.Intermediario.SomaAngulosTriangulo.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);

		int b = 60 + rand.nextInt(20);
		int e = 40 + rand.nextInt(20);
		int d = 90 - e;
		int c = d;
		int a = 180 - b - d;

//		5- Trapezio a, B, e | d
		ConfigTrapezio config = new ConfigTrapezio(a, b, c, d, e);
		config.a.mostrar();
		config.b.mostrar();
		config.e.mostrar();
		config.b.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + b + "°";
		resolucaoLatex = resolucao(a, b, c, d, e);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.d.mostrar();
		config.d.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e)
	{
		resolucaoLatex = ResolucaoSAT2.complemento90("y", e);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", a, d);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
