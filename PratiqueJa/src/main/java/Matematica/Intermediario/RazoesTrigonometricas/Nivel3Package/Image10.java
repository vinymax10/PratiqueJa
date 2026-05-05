package Matematica.Intermediario.RazoesTrigonometricas.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosBase;
import Modelo.Matematica.Conta;

public class Image10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image10(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="30°";
		dados.strAngleAltura=angle;

		int y=1+rand.nextInt(10);
		dados.altura=y*y*3;

		dados.strAltura="";
		dados.strBase="x";
		dados.strAltura="\\sqrt{"+dados.altura+"}";
		dados.strHipotenusa="";

		pergunta="Qual o valor de x?";
		
		resultadoCorreto = ""+y*3;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.tag30CAX(dados.altura);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
//		Config config = new Config1(dados);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image10(1);
	}
}
