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

public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image4(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AC,triangulo);
	
		Dados dados=config.getDados();
		String angleA=LetrasGregas.getLetra();
		String angleC=LetrasGregas.getLetra(angleA);
		dados.strAngleA=angleA;
		dados.strAngleC=angleC;
		dados.strLadoC="x";
		dados.strLadoB="";

		pergunta="Se o \\(sen~"+dados.strAngleA+"="+dados.senAngleA.showDfrac()+"\\) "
		+ "e \\(sen~"+dados.strAngleC+"="+dados.senAngleC.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.ladoC.toString();
		
		resolucaoLatex = Resolucao.leiSenoNumerador(angleC, angleA, dados.ladoA, dados.senAngleC, dados.senAngleA);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
