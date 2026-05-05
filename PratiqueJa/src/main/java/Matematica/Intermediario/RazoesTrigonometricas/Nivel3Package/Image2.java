package Matematica.Intermediario.RazoesTrigonometricas.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosBase;
import Modelo.Matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="30°";
		dados.strAngleAltura=angle;

		dados.altura=rand.nextInt(100);
		dados.hipotenusa=dados.altura*2;

		dados.strBase="";
		dados.strAltura=dados.altura+"";
		dados.strHipotenusa="x";
		
		pergunta="Qual o valor de x?";

		resultadoCorreto = ""+dados.hipotenusa;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.sen30HX(dados.altura);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
//		Config config = new Config1(dados);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
