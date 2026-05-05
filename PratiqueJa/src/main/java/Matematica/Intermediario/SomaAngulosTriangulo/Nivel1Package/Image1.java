package Matematica.Intermediario.SomaAngulosTriangulo.Nivel1Package;

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

		int a = 50 + rand.nextInt(30);

		int b = 90 - a;

//		1- TrianguloRetangulo a, B | 
		ConfigTrianguloRetangulo config = new ConfigTrianguloRetangulo(a, b);
		config.a.mostrar();
		config.b.mostrar();
		config.b.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + b + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", a, 90);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
//		Graphics.salvar(image,true,"somaAngulosTriangulo.PNG");
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
