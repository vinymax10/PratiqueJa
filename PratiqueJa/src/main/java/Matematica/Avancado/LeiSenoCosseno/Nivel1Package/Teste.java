package Matematica.Avancado.LeiSenoCosseno.Nivel1Package;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import Auxiliar.Graphics;
import Matematica.Avancado.LeiSenoCosseno.Resolucao;
import Matematica.Avancado.LeiSenoCosseno.Config.Config;
import Matematica.Avancado.LeiSenoCosseno.Config.Dados;
import Matematica.Avancado.LeiSenoCosseno.Config.TipoDado;
import Matematica.Avancado.LeiSenoCosseno.Config.Triangulo;
import Matematica.Avancado.LeiSenoCosseno.Config.Triangulos;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.LetrasGregas;
import Modelo.Matematica.Conta;

public class Teste extends Conta
{
	private static final long serialVersionUID = 1L;

	public Teste(int index)
	{
		super(index);
		for(int i = 0; i < Triangulos.triangulos.length; i++)
		{
			Triangulo triangulo = Triangulos.triangulos[i];
			Config config = new Config(TipoDado.AB, triangulo);

			Dados dados = config.getDados();
			dados.strAngleA = "AA";
			dados.strAngleB = "BB";
			dados.strAngleC = "CC";
			dados.strLadoA = "AA";
			dados.strLadoB = "BB";
			dados.strLadoC = "CC";

			BufferedImage image = config.criarImagem(index);

			baos = Graphics.salvar(image, false, "");
			carregarBlob();

//			Graphics.salvar(image, true, "triangulo//LeiSenoCosseno" + (i+1) + ".PNG");
		}
	}

	public static void main(String[] args)
	{
		new Teste(1);
	}
}
