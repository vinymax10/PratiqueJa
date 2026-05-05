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

public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image5(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.BC,triangulo);
	
		Dados dados=config.getDados();
		String angleB=LetrasGregas.getLetra();
		String angleC=LetrasGregas.getLetra(angleB);
		dados.strAngleB=angleB;
		dados.strAngleC=angleC;
		dados.strLadoB="x";
		dados.strLadoA="";

		pergunta="Se o \\(sen~"+dados.strAngleB+"="+dados.senAngleB.showDfrac()+"\\) "
		+ "e \\(sen~"+dados.strAngleC+"="+dados.senAngleC.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.ladoB.toString();
		
		resolucaoLatex = Resolucao.leiSenoNumerador(angleB, angleC, dados.ladoC, dados.senAngleB, dados.senAngleC);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
