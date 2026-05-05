package Matematica.Intermediario.SomaAngulosTriangulo.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Matematica.Intermediario.SomaAngulosTriangulo.Nivel2Package.ConfigTrianguloBipartido2;
import Modelo.Matematica.Conta;


public class Image22 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image22(int index)
	{
		super(index);

		int a = 40 + rand.nextInt(10);
		int b = 50 + rand.nextInt(10);
		int e = 40 + rand.nextInt(10);
		int c = 180 - a - b;
		int d = 180 - c;
		int f = 180 - b - e - a;

//		22- TrianguloBipartido2 a, b, e, F | 
		ConfigTrianguloBipartido2 config = new ConfigTrianguloBipartido2(a, b, c, d, e, f);
		config.f.mostrar();
		config.e.mostrar();
		config.b.mostrar();
		config.a.mostrar();
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
		new Image22(1);
	}
}
