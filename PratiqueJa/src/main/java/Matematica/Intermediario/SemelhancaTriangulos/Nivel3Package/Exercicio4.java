package Matematica.Intermediario.SemelhancaTriangulos.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.SemelhancaTriangulos.ConfigValores2;
import Matematica.Intermediario.SemelhancaTriangulos.Nivel2Package.ConfigSemelhancaTriangulos4;
import Modelo.Matematica.Conta;


public class Exercicio4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio4(int index)
	{
		super(index);
		int pos = rand.nextInt(4);
		ConfigValores2 configValores = new ConfigValores2(pos,true);

		ConfigSemelhancaTriangulos4 config = new ConfigSemelhancaTriangulos4(configValores);
		
		resultadoCorreto=configValores.resultado.toString()+"";

		resolucaoLatex = configValores.resolucaoLatex;
		
		textLatex = config.getTextLatex();
		
		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Exercicio4(1);
	}

}
