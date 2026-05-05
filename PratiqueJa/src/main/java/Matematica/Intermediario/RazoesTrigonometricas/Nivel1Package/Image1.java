package Matematica.Intermediario.RazoesTrigonometricas.Nivel1Package;

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

		pergunta="Qual o \\(sen~"+angle+"\\)?";

		resultadoCorreto = ""+dados.senAngleAltura;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.sen(angle,dados.altura,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
//		Graphics.salvar(image, true, "RazoesTrigonometricas.PNG");
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
