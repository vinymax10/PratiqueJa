package matematica.intermediario.semelhancatriangulos.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.semelhancatriangulos.ConfigValores2;
import modelo.matematica.Conta;

public class Exercicio3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio3(int index)
	{
		super(index);
		int pos = rand.nextInt(4);
		ConfigValores2 configValores = new ConfigValores2(pos,false);
		ConfigSemelhancaTriangulos6 config = new ConfigSemelhancaTriangulos6(configValores);
		
		resultadoCorreto=configValores.incognita.toString();
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
