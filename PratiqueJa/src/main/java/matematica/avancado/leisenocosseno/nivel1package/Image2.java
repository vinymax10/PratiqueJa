package matematica.avancado.leisenocosseno.nivel1package;

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
