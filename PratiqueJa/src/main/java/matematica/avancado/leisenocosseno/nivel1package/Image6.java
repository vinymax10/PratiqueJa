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

public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image6(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.BC,triangulo);
	
		Dados dados=config.getDados();
		String angleB=LetrasGregas.getLetra();
		String angleC=LetrasGregas.getLetra(angleB);
		dados.strAngleB=angleB;
		dados.strAngleC=angleC;
		dados.strLadoC="x";
		dados.strLadoA="";

		pergunta="Se o \\(sen~"+dados.strAngleB+"="+dados.senAngleB.showDfrac()+"\\) "
		+ "e \\(sen~"+dados.strAngleC+"="+dados.senAngleC.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.ladoC.toString();
		
		resolucaoLatex = Resolucao.leiSenoNumerador(angleC, angleB, dados.ladoB, dados.senAngleC, dados.senAngleB);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
