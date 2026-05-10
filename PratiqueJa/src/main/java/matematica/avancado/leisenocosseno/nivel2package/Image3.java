package matematica.avancado.leisenocosseno.nivel2package;

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
