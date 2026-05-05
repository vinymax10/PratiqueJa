package Matematica.Avancado.LeiSenoCosseno.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Avancado.LeiSenoCosseno.Resolucao;
import Matematica.Avancado.LeiSenoCosseno.Config.Config;
import Matematica.Avancado.LeiSenoCosseno.Config.Dados;
import Matematica.Avancado.LeiSenoCosseno.Config.TipoDado;
import Matematica.Avancado.LeiSenoCosseno.Config.Triangulo;
import Matematica.Avancado.LeiSenoCosseno.Config.Triangulos;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.LetrasGregas;
import Modelo.Matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image2(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AB,triangulo);
		
		Dados dados=config.getDados();
		String angleA=LetrasGregas.getLetra();
		String angleB=LetrasGregas.getLetra(angleA);
		dados.strAngleA=angleA;
		dados.strAngleB=angleB;
		dados.strLadoB="x";
		dados.strLadoC="";

		pergunta="Se o \\(sen~"+dados.strAngleA+"="+dados.senAngleA.showDfrac()+"\\) "
		+ "e \\(sen~"+dados.strAngleB+"="+dados.senAngleB.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.ladoB.toString();
		resolucaoLatex = Resolucao.leiSenoNumerador(angleB, angleA, dados.ladoA, dados.senAngleB, dados.senAngleA);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
