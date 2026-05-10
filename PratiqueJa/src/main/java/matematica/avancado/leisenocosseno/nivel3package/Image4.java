package matematica.avancado.leisenocosseno.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.avancado.leisenocosseno.Resolucao;
import matematica.avancado.leisenocosseno.config.Config;
import matematica.avancado.leisenocosseno.config.Dados;
import matematica.avancado.leisenocosseno.config.TipoDado;
import matematica.avancado.leisenocosseno.config.Triangulo;
import matematica.avancado.leisenocosseno.config.Triangulos;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

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
