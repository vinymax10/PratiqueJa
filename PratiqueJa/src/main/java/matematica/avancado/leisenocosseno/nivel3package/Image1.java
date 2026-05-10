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

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image1(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AB,triangulo);
		
		Dados dados=config.getDados();
		String angle=LetrasGregas.getLetra();
		dados.strAngleA=angle;
		dados.strLadoA="x";

		pergunta="Se o \\(cos~"+dados.strAngleA+"="+dados.cosAngleA.showDfrac()+"\\), "
		+ "qual o valor de x?";

		resultadoCorreto = ""+dados.ladoA.toString();
		resolucaoLatex = Resolucao.leiCosLado(angle, dados.ladoB, dados.ladoC, dados.cosAngleA);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
