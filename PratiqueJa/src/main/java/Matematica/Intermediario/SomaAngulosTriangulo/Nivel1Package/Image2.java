package Matematica.Intermediario.SomaAngulosTriangulo.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSAT2;
import Modelo.Matematica.Conta;


public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int a = 50 + rand.nextInt(30);
		int b = 90 - a;

//		2- TrianguloRetangulo A, b | 
		ConfigTrianguloRetangulo config = new ConfigTrianguloRetangulo(a, b);
		config.a.mostrar();
		config.b.mostrar();
		config.a.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + a + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", b, 90);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
