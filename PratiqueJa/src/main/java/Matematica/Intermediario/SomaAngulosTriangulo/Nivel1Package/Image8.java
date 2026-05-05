package Matematica.Intermediario.SomaAngulosTriangulo.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int index)
	{
		super(index);

		int b = 20 + rand.nextInt(10);
		int a = 20 + rand.nextInt(10);
		int c = 180 - b - a;

//		8- ConfigTriangulo2 a, b, C | 
		ConfigTriangulo2 config = new ConfigTriangulo2(a, b, c);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.c.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + c + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", a, b);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}
}
