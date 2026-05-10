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
