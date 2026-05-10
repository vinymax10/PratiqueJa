package matematica.intermediario.semelhancatriangulos.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.semelhancatriangulos.ConfigValores1;
import matematica.intermediario.semelhancatriangulos.nivel1package.ConfigSemelhancaTriangulos3;
import modelo.matematica.Conta;

public class Exercicio3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio3(int index)
	{
		super(index);
		int pos = rand.nextInt(4);
		ConfigValores1 configValores = new ConfigValores1(pos,true);

		ConfigSemelhancaTriangulos3 config = new ConfigSemelhancaTriangulos3(configValores);
		
		resultadoCorreto=configValores.resultado.toString()+"";

		resolucaoLatex = configValores.resolucaoLatex;
		
		textLatex = config.getTextLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Exercicio3(1);
	}

}
