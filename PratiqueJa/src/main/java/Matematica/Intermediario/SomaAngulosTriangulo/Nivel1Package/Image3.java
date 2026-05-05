package Matematica.Intermediario.SomaAngulosTriangulo.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);

		int b = 50 + rand.nextInt(20);
		int c = 20 + rand.nextInt(20);
		int a = 180 - b - c;

//		3- ConfigTriangulo1 A, b, c | 
		ConfigTriangulo1 config = new ConfigTriangulo1(a, b, c);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.a.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + a + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", b, c);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image3(1);
	}
}
