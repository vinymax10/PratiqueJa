package Matematica.Intermediario.RazoesTrigonometricas.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosBase;
import Modelo.Matematica.Conta;

public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="30°";
		dados.strAngleAltura=angle;

		int y=1+rand.nextInt(10);
		dados.base=y*y*3;

		dados.strAltura="";
		dados.strBase="\\sqrt{"+dados.base+"}";
		dados.strHipotenusa="x";
		
		pergunta="Qual o valor de x?";
		
		resultadoCorreto = ""+y*2;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cos30HX(dados.base);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
//		Config config = new Config1(dados);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
