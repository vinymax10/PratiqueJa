package Matematica.Intermediario.RazoesTrigonometricas.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosBase;
import Modelo.Matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="30°";
		dados.strAngleAltura=angle;
		dados.strBase="";
		dados.strAltura="x";
		dados.altura=rand.nextInt(100);
		dados.hipotenusa=dados.altura*2;
		dados.strHipotenusa=dados.hipotenusa+"";
		
		pergunta="Qual o valor de x?";

		resultadoCorreto = ""+dados.altura;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.sen30COX(dados.hipotenusa);

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
