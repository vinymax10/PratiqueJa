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

public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image4(int index)
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

		pergunta="Se o \\(sen~"+dados.strAngleB+"="+dados.senAngleB.showDfrac()+"\\), qual o valor de \\(sen~"+dados.strAngleA+"\\)?";

		resultadoCorreto = ""+dados.senAngleA.toString();
		resolucaoLatex = Resolucao.leiSenoDenominador(angleA, angleB, dados.ladoA, dados.ladoB, dados.senAngleB);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
