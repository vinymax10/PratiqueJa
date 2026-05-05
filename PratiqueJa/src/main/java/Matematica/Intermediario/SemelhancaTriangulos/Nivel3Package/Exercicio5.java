package Matematica.Intermediario.SemelhancaTriangulos.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SemelhancaTriangulos.ConfigValores2;
import Matematica.Intermediario.SemelhancaTriangulos.Nivel2Package.ConfigSemelhancaTriangulos5;
import Modelo.Matematica.Conta;


public class Exercicio5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio5(int index)
	{
		super(index);
		int pos = rand.nextInt(4);
		ConfigValores2 configValores = new ConfigValores2(pos,true);

		ConfigSemelhancaTriangulos5 config = new ConfigSemelhancaTriangulos5(configValores);
		
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
