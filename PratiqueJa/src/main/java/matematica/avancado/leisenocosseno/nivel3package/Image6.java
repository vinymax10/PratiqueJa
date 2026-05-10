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

public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	
	public Image6(int index)
	{
		super(index);
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AC,triangulo);
	
		Dados dados=config.getDados();
		String angleA=LetrasGregas.getLetra();
		String angleC=LetrasGregas.getLetra(angleA);
		dados.strAngleA=angleA;
		dados.strAngleC=angleC;
		dados.strLadoB="";

		pergunta="Se o \\(sen~"+dados.strAngleC+"="+dados.senAngleC.showDfrac()+"\\), qual o valor de \\(sen~"+dados.strAngleA+"\\)?";

		resultadoCorreto = ""+dados.senAngleA.toString();
		resolucaoLatex = Resolucao.leiSenoDenominador(angleA, angleC, dados.ladoA, dados.ladoC, dados.senAngleC);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
