package Matematica.Intermediario.RazoesTrigonometricas.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosAltura;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.LetrasGregas;
import Modelo.Matematica.Conta;

public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);

		Dados dados=new DadosAltura();
		String angle=LetrasGregas.getLetra();
		dados.strAngleAltura=angle;
		dados.strAltura="";
		dados.strBase="x";

		pergunta="Se o \\(cos~"+angle+"="+dados.cosAngleAltura.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.base;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cosCAX(angle,dados.cosAngleAltura,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image3(1);
	}
}
