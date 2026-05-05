package Matematica.Avancado.LeiSenoCosseno.Nivel3Package;

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

public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image5(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AB,triangulo);
		
		Dados dados=config.getDados();
		String angleA=LetrasGregas.getLetra();
		String angleB=LetrasGregas.getLetra(angleA);
		dados.strAngleA=angleA;
		dados.strAngleB=angleB;
		dados.strLadoC="";

		pergunta="Se o \\(sen~"+dados.strAngleA+"="+dados.senAngleA.showDfrac()+"\\), qual o valor de \\(sen~"+dados.strAngleB+"\\)?";

		resultadoCorreto = ""+dados.senAngleB.toString();
		resolucaoLatex = Resolucao.leiSenoDenominador(angleB, angleA, dados.ladoB, dados.ladoA, dados.senAngleA);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
