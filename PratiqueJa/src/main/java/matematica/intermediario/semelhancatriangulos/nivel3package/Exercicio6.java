package matematica.intermediario.semelhancatriangulos.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.semelhancatriangulos.ConfigValores2;
import matematica.intermediario.semelhancatriangulos.nivel2package.ConfigSemelhancaTriangulos6;
import modelo.matematica.Conta;

public class Exercicio6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio6(int index)
	{
		super(index);
		int pos = rand.nextInt(4);

		ConfigValores2 configValores = new ConfigValores2(pos,true);

		ConfigSemelhancaTriangulos6 config = new ConfigSemelhancaTriangulos6(configValores);
		
		resultadoCorreto=configValores.resultado.toString()+"";

		resolucaoLatex = configValores.resolucaoLatex;
		
		textLatex = config.getTextLatex();
		
		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Exercicio6(1);
	}

}
