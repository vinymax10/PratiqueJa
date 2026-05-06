package matematica.avancado.leisenocosseno.nivel3package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.avancado.leisenocosseno.Resolucao;
import matematica.avancado.leisenocosseno.config.Config;
import matematica.avancado.leisenocosseno.config.Dados;
import matematica.avancado.leisenocosseno.config.TipoDado;
import matematica.avancado.leisenocosseno.config.Triangulo;
import matematica.avancado.leisenocosseno.config.Triangulos;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

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
