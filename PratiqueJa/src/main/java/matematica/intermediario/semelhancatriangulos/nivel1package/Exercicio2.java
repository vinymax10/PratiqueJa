package matematica.intermediario.semelhancatriangulos.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.intermediario.semelhancatriangulos.ConfigValores1;
import modelo.matematica.Conta;

public class Exercicio2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio2(int index)
	{
		super(index);
		int pos = rand.nextInt(4);
		ConfigValores1 configValores = new ConfigValores1(pos,false);

		ConfigSemelhancaTriangulos2 config = new ConfigSemelhancaTriangulos2(configValores);
		
		resultadoCorreto=configValores.incognita.toString();

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
