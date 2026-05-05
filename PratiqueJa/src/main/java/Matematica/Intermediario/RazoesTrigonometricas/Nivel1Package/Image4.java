package Matematica.Intermediario.RazoesTrigonometricas.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosAltura;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.LetrasGregas;
import Modelo.Matematica.Conta;

public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);
		
		Dados dados=new DadosAltura();
		String angle=LetrasGregas.getLetra();
		dados.strAngleBase=angle;
		dados.strAltura="";
		
		pergunta="Qual o \\(sen~"+angle+"\\)?";

		resultadoCorreto = ""+dados.senAngleBase;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.sen(angle,dados.base,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
