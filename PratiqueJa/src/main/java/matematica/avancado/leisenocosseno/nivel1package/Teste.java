package matematica.avancado.leisenocosseno.nivel1package;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import infra.Graphics;
import matematica.avancado.leisenocosseno.Resolucao;
import matematica.avancado.leisenocosseno.config.Config;
import matematica.avancado.leisenocosseno.config.Dados;
import matematica.avancado.leisenocosseno.config.TipoDado;
import matematica.avancado.leisenocosseno.config.Triangulo;
import matematica.avancado.leisenocosseno.config.Triangulos;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

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
