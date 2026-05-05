package Matematica.Intermediario.RazoesTrigonometricas.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosBase;
import Modelo.Matematica.Conta;

public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image7(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="60°";
		dados.strAngleBase=angle;
		dados.strBase="";
		dados.strAltura="x";
		dados.altura=rand.nextInt(100);
		dados.hipotenusa=dados.altura*2;
		dados.strHipotenusa=dados.hipotenusa+"";
		
		pergunta="Qual o valor de x?";

		resultadoCorreto = ""+dados.altura;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cos60COX(dados.hipotenusa);

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
