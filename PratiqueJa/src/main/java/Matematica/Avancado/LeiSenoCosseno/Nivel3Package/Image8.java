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

public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image8(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.BC,triangulo);
	
		Dados dados=config.getDados();
		String angleB=LetrasGregas.getLetra();
		String angleC=LetrasGregas.getLetra(angleB);
		dados.strAngleB=angleB;
		dados.strAngleC=angleC;
		dados.strLadoA="";

		pergunta="Se o \\(sen~"+dados.strAngleC+"="+dados.senAngleC.showDfrac()+"\\), qual o valor de \\(sen~"+dados.strAngleB+"\\)?";

		resultadoCorreto = ""+dados.senAngleB.toString();
		resolucaoLatex = Resolucao.leiSenoDenominador(angleB, angleC, dados.ladoB, dados.ladoC, dados.senAngleC);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}
}
