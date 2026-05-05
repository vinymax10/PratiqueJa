package Matematica.Intermediario.SomaAngulosTriangulo.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Matematica.Intermediario.SomaAngulosTriangulo.Nivel2Package.ConfigTrianguloBipartido1;
import Modelo.Matematica.Conta;


public class Image15 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image15(int index)
	{
		super(index);

		int b = 55 + rand.nextInt(15);
		int e = 10 + rand.nextInt(15);
		int f = 25 + rand.nextInt(15);
		int c = e + f;
		int a = 180 - b - c;
		int d = 180 - c;

//		15- TrianguloBipartido1 a, b, e, F | 
		ConfigTrianguloBipartido1 config = new ConfigTrianguloBipartido1(a, b, c, d, e, f);
		config.a.mostrar();
		config.b.mostrar();
		config.f.mostrar();
		config.e.mostrar();
		config.f.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + f + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", a, b, e);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image15(1);
	}
}
