package matematica.intermediario.semelhancatriangulos.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.semelhancatriangulos.ConfigValores1;
import matematica.intermediario.semelhancatriangulos.nivel1package.ConfigSemelhancaTriangulos2;
import modelo.matematica.Conta;

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
