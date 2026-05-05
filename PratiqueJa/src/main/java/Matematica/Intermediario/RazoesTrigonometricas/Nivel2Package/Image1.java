package Matematica.Intermediario.RazoesTrigonometricas.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosBase;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.LetrasGregas;
import Modelo.Matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		Dados dados=new DadosBase();
		String angle=LetrasGregas.getLetra();
		dados.strAngleAltura=angle;
		dados.strBase="";
		dados.strAltura="x";

		pergunta="Se o \\(sen~"+angle+"="+dados.senAngleAltura.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.altura;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.senCOX(angle,dados.senAngleAltura,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
