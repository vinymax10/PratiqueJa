package Matematica.Avancado.LeiSenoCosseno.Nivel2Package;

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

public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image3(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AB,triangulo);
		
		Dados dados=config.getDados();
		String angle=LetrasGregas.getLetra();
		dados.strAngleC=angle;

		pergunta="Qual o \\(cos~"+dados.strAngleC+"\\)?";

		resultadoCorreto = ""+dados.cosAngleC.toString();
		resolucaoLatex = Resolucao.leiCosCosseno(angle, dados.ladoC,dados.ladoA, dados.ladoB);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image3(1);
	}
}
