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

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image2(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AB,triangulo);
		
		Dados dados=config.getDados();
		String angle=LetrasGregas.getLetra();
		dados.strAngleB=angle;
		dados.strLadoB="x";

		pergunta="Se o \\(cos~"+dados.strAngleB+"="+dados.cosAngleB.showDfrac()+"\\), "
		+ "qual o valor de x?";

		resultadoCorreto = ""+dados.ladoB.toString();
		resolucaoLatex = Resolucao.leiCosLado(angle, dados.ladoA, dados.ladoC, dados.cosAngleB);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
