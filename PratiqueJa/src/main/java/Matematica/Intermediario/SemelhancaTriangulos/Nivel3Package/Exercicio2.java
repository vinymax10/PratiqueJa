package Matematica.Intermediario.SemelhancaTriangulos.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SemelhancaTriangulos.ConfigValores1;
import Matematica.Intermediario.SemelhancaTriangulos.Nivel1Package.ConfigSemelhancaTriangulos2;
import Modelo.Matematica.Conta;

public class Exercicio2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio2(int index)
	{
		super(index);
		int pos = rand.nextInt(4);
		ConfigValores1 configValores = new ConfigValores1(pos,true);

		ConfigSemelhancaTriangulos2 config = new ConfigSemelhancaTriangulos2(configValores);
		
		resultadoCorreto=configValores.resultado.toString()+"";

		resolucaoLatex = configValores.resolucaoLatex;
		
		textLatex = config.getTextLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Exercicio2(1);
	}

}
