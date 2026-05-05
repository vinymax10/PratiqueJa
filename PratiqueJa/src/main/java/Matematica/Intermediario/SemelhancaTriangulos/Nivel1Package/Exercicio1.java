package Matematica.Intermediario.SemelhancaTriangulos.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SemelhancaTriangulos.ConfigValores1;
import Modelo.Matematica.Conta;

public class Exercicio1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio1(int index)
	{
		super(index);
		int pos = rand.nextInt(4);
		ConfigValores1 configValores = new ConfigValores1(pos,false);

		ConfigSemelhancaTriangulos1 config = new ConfigSemelhancaTriangulos1(configValores);
		
		resultadoCorreto=configValores.incognita.toString();

		resolucaoLatex = configValores.resolucaoLatex;
		
		textLatex = config.getTextLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
//		Graphics.salvar(image, true, "semelhancaTriangulos.PNG");
	}

	public static void main(String[] args)
	{
		new Exercicio1(1);
	}

}
