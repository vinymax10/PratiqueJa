package Matematica.Intermediario.RazoesTrigonometricas.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosAltura;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.LetrasGregas;
import Modelo.Matematica.Conta;

public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image7(int index)
	{
		super(index);

		Dados dados=new DadosAltura();
		String angle=LetrasGregas.getLetra();
		dados.strAngleBase=angle;
		dados.strBase="x";
		dados.strAltura="";

		pergunta="Se o \\(sen~"+angle+"="+dados.senAngleBase.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.base;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.senCOX(angle,dados.senAngleBase,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image7(1);
	}
}
