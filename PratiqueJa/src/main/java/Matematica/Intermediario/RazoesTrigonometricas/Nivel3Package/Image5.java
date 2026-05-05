package Matematica.Intermediario.RazoesTrigonometricas.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Racional;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosBase;
import Modelo.Matematica.Conta;

public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="30°";
		dados.strAngleAltura=angle;

		int y=1+rand.nextInt(10);
		dados.hipotenusa=y*y*3;

		dados.strAltura="";
		dados.strBase="x";
		dados.strHipotenusa="\\sqrt{"+dados.hipotenusa+"}";
		
		pergunta="Qual o valor de x?";
		Racional resultado=new Racional(3*y,2);
		resultado.fatoracao(2);
		
		resultadoCorreto = resultado.toString();
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cos30COX(dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
//		Config config = new Config1(dados);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
